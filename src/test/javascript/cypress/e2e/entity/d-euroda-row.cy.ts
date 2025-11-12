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

describe('DEurodaRow e2e test', () => {
  const dEurodaRowPageUrl = '/d-euroda-row';
  const dEurodaRowPageUrlPattern = new RegExp('/d-euroda-row(\\?.*)?$');
  const username = Cypress.env('E2E_USERNAME') ?? 'user';
  const password = Cypress.env('E2E_PASSWORD') ?? 'user';
  const dEurodaRowSample = { euFamil: 'yuck', euImena: 'um during', euNacBel: 'designation completion', euRodstvo: 'suckle' };

  let dEurodaRow;

  beforeEach(() => {
    cy.login(username, password);
  });

  beforeEach(() => {
    cy.intercept('GET', '/api/d-euroda-rows+(?*|)').as('entitiesRequest');
    cy.intercept('POST', '/api/d-euroda-rows').as('postEntityRequest');
    cy.intercept('DELETE', '/api/d-euroda-rows/*').as('deleteEntityRequest');
  });

  afterEach(() => {
    if (dEurodaRow) {
      cy.authenticatedRequest({
        method: 'DELETE',
        url: `/api/d-euroda-rows/${dEurodaRow.id}`,
      }).then(() => {
        dEurodaRow = undefined;
      });
    }
  });

  it('DEurodaRows menu should load DEurodaRows page', () => {
    cy.visit('/');
    cy.clickOnEntityMenuItem('d-euroda-row');
    cy.wait('@entitiesRequest').then(({ response }) => {
      if (response?.body.length === 0) {
        cy.get(entityTableSelector).should('not.exist');
      } else {
        cy.get(entityTableSelector).should('exist');
      }
    });
    cy.getEntityHeading('DEurodaRow').should('exist');
    cy.url().should('match', dEurodaRowPageUrlPattern);
  });

  describe('DEurodaRow page', () => {
    describe('create button click', () => {
      beforeEach(() => {
        cy.visit(dEurodaRowPageUrl);
        cy.wait('@entitiesRequest');
      });

      it('should load create DEurodaRow page', () => {
        cy.get(entityCreateButtonSelector).click();
        cy.url().should('match', new RegExp('/d-euroda-row/new$'));
        cy.getEntityCreateUpdateHeading('DEurodaRow');
        cy.get(entityCreateSaveButtonSelector).should('exist');
        cy.get(entityCreateCancelButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response?.statusCode).to.equal(200);
        });
        cy.url().should('match', dEurodaRowPageUrlPattern);
      });
    });

    describe('with existing value', () => {
      beforeEach(() => {
        cy.authenticatedRequest({
          method: 'POST',
          url: '/api/d-euroda-rows',
          body: dEurodaRowSample,
        }).then(({ body }) => {
          dEurodaRow = body;

          cy.intercept(
            {
              method: 'GET',
              url: '/api/d-euroda-rows+(?*|)',
              times: 1,
            },
            {
              statusCode: 200,
              headers: {
                link: '<http://localhost/api/d-euroda-rows?page=0&size=20>; rel="last",<http://localhost/api/d-euroda-rows?page=0&size=20>; rel="first"',
              },
              body: [dEurodaRow],
            },
          ).as('entitiesRequestInternal');
        });

        cy.visit(dEurodaRowPageUrl);

        cy.wait('@entitiesRequestInternal');
      });

      it('detail button click should load details DEurodaRow page', () => {
        cy.get(entityDetailsButtonSelector).first().click();
        cy.getEntityDetailsHeading('dEurodaRow');
        cy.get(entityDetailsBackButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response?.statusCode).to.equal(200);
        });
        cy.url().should('match', dEurodaRowPageUrlPattern);
      });

      it('edit button click should load edit DEurodaRow page and go back', () => {
        cy.get(entityEditButtonSelector).first().click();
        cy.getEntityCreateUpdateHeading('DEurodaRow');
        cy.get(entityCreateSaveButtonSelector).should('exist');
        cy.get(entityCreateCancelButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response?.statusCode).to.equal(200);
        });
        cy.url().should('match', dEurodaRowPageUrlPattern);
      });

      it('edit button click should load edit DEurodaRow page and save', () => {
        cy.get(entityEditButtonSelector).first().click();
        cy.getEntityCreateUpdateHeading('DEurodaRow');
        cy.get(entityCreateSaveButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response?.statusCode).to.equal(200);
        });
        cy.url().should('match', dEurodaRowPageUrlPattern);
      });

      it('last delete button click should delete instance of DEurodaRow', () => {
        cy.intercept('GET', '/api/d-euroda-rows/*').as('dialogDeleteRequest');
        cy.get(entityDeleteButtonSelector).last().click();
        cy.wait('@dialogDeleteRequest');
        cy.getEntityDeleteDialogHeading('dEurodaRow').should('exist');
        cy.get(entityConfirmDeleteButtonSelector).click();
        cy.wait('@deleteEntityRequest').then(({ response }) => {
          expect(response?.statusCode).to.equal(204);
        });
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response?.statusCode).to.equal(200);
        });
        cy.url().should('match', dEurodaRowPageUrlPattern);

        dEurodaRow = undefined;
      });
    });
  });

  describe('new DEurodaRow page', () => {
    beforeEach(() => {
      cy.visit(`${dEurodaRowPageUrl}`);
      cy.get(entityCreateButtonSelector).click();
      cy.getEntityCreateUpdateHeading('DEurodaRow');
    });

    it('should create an instance of DEurodaRow', () => {
      cy.get(`[data-cy="euFamil"]`).type('once whereas');
      cy.get(`[data-cy="euFamil"]`).should('have.value', 'once whereas');

      cy.get(`[data-cy="euImena"]`).type('whether');
      cy.get(`[data-cy="euImena"]`).should('have.value', 'whether');

      cy.get(`[data-cy="euNacBel"]`).type('in charlatan lyre');
      cy.get(`[data-cy="euNacBel"]`).should('have.value', 'in charlatan lyre');

      cy.get(`[data-cy="euRodstvo"]`).type('despite selfishly famously');
      cy.get(`[data-cy="euRodstvo"]`).should('have.value', 'despite selfishly famously');

      cy.get(entityCreateSaveButtonSelector).click();

      cy.wait('@postEntityRequest').then(({ response }) => {
        expect(response?.statusCode).to.equal(201);
        dEurodaRow = response.body;
      });
      cy.wait('@entitiesRequest').then(({ response }) => {
        expect(response?.statusCode).to.equal(200);
      });
      cy.url().should('match', dEurodaRowPageUrlPattern);
    });
  });
});
