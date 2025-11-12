import {
  entityConfirmDeleteButtonSelector,
  entityCreateButtonSelector,
  entityCreateCancelButtonSelector,
  entityCreateSaveButtonSelector,
  entityDeleteButtonSelector,
  entityDetailsBackButtonSelector,
  entityDetailsButtonSelector,
  entityEditButtonSelector,
  entityTableSelector,
} from '../../support/entity';

describe('DVoitRow e2e test', () => {
  const dVoitRowPageUrl = '/d-voit-row';
  const dVoitRowPageUrlPattern = new RegExp('/d-voit-row(\\?.*)?$');
  const username = Cypress.env('E2E_USERNAME') ?? 'user';
  const password = Cypress.env('E2E_PASSWORD') ?? 'user';
  const dVoitRowSample = {
    vnom: 'solder sadly responsibility',
    vime: 'thrifty hoarse',
    bgime: 'syringe quietly',
    bgadres: 'sharply whereas awesome',
  };

  let dVoitRow;

  beforeEach(() => {
    cy.login(username, password);
  });

  beforeEach(() => {
    cy.intercept('GET', '/api/d-voit-rows+(?*|)').as('entitiesRequest');
    cy.intercept('POST', '/api/d-voit-rows').as('postEntityRequest');
    cy.intercept('DELETE', '/api/d-voit-rows/*').as('deleteEntityRequest');
  });

  afterEach(() => {
    if (dVoitRow) {
      cy.authenticatedRequest({
        method: 'DELETE',
        url: `/api/d-voit-rows/${dVoitRow.id}`,
      }).then(() => {
        dVoitRow = undefined;
      });
    }
  });

  it('DVoitRows menu should load DVoitRows page', () => {
    cy.visit('/');
    cy.clickOnEntityMenuItem('d-voit-row');
    cy.wait('@entitiesRequest').then(({ response }) => {
      if (response?.body.length === 0) {
        cy.get(entityTableSelector).should('not.exist');
      } else {
        cy.get(entityTableSelector).should('exist');
      }
    });
    cy.getEntityHeading('DVoitRow').should('exist');
    cy.url().should('match', dVoitRowPageUrlPattern);
  });

  describe('DVoitRow page', () => {
    describe('create button click', () => {
      beforeEach(() => {
        cy.visit(dVoitRowPageUrl);
        cy.wait('@entitiesRequest');
      });

      it('should load create DVoitRow page', () => {
        cy.get(entityCreateButtonSelector).click();
        cy.url().should('match', new RegExp('/d-voit-row/new$'));
        cy.getEntityCreateUpdateHeading('DVoitRow');
        cy.get(entityCreateSaveButtonSelector).should('exist');
        cy.get(entityCreateCancelButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response?.statusCode).to.equal(200);
        });
        cy.url().should('match', dVoitRowPageUrlPattern);
      });
    });

    describe('with existing value', () => {
      beforeEach(() => {
        cy.authenticatedRequest({
          method: 'POST',
          url: '/api/d-voit-rows',
          body: dVoitRowSample,
        }).then(({ body }) => {
          dVoitRow = body;

          cy.intercept(
            {
              method: 'GET',
              url: '/api/d-voit-rows+(?*|)',
              times: 1,
            },
            {
              statusCode: 200,
              headers: {
                link: '<http://localhost/api/d-voit-rows?page=0&size=20>; rel="last",<http://localhost/api/d-voit-rows?page=0&size=20>; rel="first"',
              },
              body: [dVoitRow],
            },
          ).as('entitiesRequestInternal');
        });

        cy.visit(dVoitRowPageUrl);

        cy.wait('@entitiesRequestInternal');
      });

      it('detail button click should load details DVoitRow page', () => {
        cy.get(entityDetailsButtonSelector).first().click();
        cy.getEntityDetailsHeading('dVoitRow');
        cy.get(entityDetailsBackButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response?.statusCode).to.equal(200);
        });
        cy.url().should('match', dVoitRowPageUrlPattern);
      });

      it('edit button click should load edit DVoitRow page and go back', () => {
        cy.get(entityEditButtonSelector).first().click();
        cy.getEntityCreateUpdateHeading('DVoitRow');
        cy.get(entityCreateSaveButtonSelector).should('exist');
        cy.get(entityCreateCancelButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response?.statusCode).to.equal(200);
        });
        cy.url().should('match', dVoitRowPageUrlPattern);
      });

      it('edit button click should load edit DVoitRow page and save', () => {
        cy.get(entityEditButtonSelector).first().click();
        cy.getEntityCreateUpdateHeading('DVoitRow');
        cy.get(entityCreateSaveButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response?.statusCode).to.equal(200);
        });
        cy.url().should('match', dVoitRowPageUrlPattern);
      });

      it('last delete button click should delete instance of DVoitRow', () => {
        cy.intercept('GET', '/api/d-voit-rows/*').as('dialogDeleteRequest');
        cy.get(entityDeleteButtonSelector).last().click();
        cy.wait('@dialogDeleteRequest');
        cy.getEntityDeleteDialogHeading('dVoitRow').should('exist');
        cy.get(entityConfirmDeleteButtonSelector).click();
        cy.wait('@deleteEntityRequest').then(({ response }) => {
          expect(response?.statusCode).to.equal(204);
        });
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response?.statusCode).to.equal(200);
        });
        cy.url().should('match', dVoitRowPageUrlPattern);

        dVoitRow = undefined;
      });
    });
  });

  describe('new DVoitRow page', () => {
    beforeEach(() => {
      cy.visit(`${dVoitRowPageUrl}`);
      cy.get(entityCreateButtonSelector).click();
      cy.getEntityCreateUpdateHeading('DVoitRow');
    });

    it('should create an instance of DVoitRow', () => {
      cy.get(`[data-cy="vnom"]`).type('atop');
      cy.get(`[data-cy="vnom"]`).should('have.value', 'atop');

      cy.get(`[data-cy="vime"]`).type('spherical until');
      cy.get(`[data-cy="vime"]`).should('have.value', 'spherical until');

      cy.get(`[data-cy="bgime"]`).type('coal boring mmm');
      cy.get(`[data-cy="bgime"]`).should('have.value', 'coal boring mmm');

      cy.get(`[data-cy="bgadres"]`).type('upon wilted airbrush');
      cy.get(`[data-cy="bgadres"]`).should('have.value', 'upon wilted airbrush');

      cy.get(entityCreateSaveButtonSelector).click();

      cy.wait('@postEntityRequest').then(({ response }) => {
        expect(response?.statusCode).to.equal(201);
        dVoitRow = response.body;
      });
      cy.wait('@entitiesRequest').then(({ response }) => {
        expect(response?.statusCode).to.equal(200);
      });
      cy.url().should('match', dVoitRowPageUrlPattern);
    });
  });
});
