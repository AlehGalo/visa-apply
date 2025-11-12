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

describe('DMaikaRow e2e test', () => {
  const dMaikaRowPageUrl = '/d-maika-row';
  const dMaikaRowPageUrlPattern = new RegExp('/d-maika-row(\\?.*)?$');
  const username = Cypress.env('E2E_USERNAME') ?? 'user';
  const password = Cypress.env('E2E_PASSWORD') ?? 'user';
  const dMaikaRowSample = {
    dcFamil: 'of gah oof',
    dcImena: 'ha',
    dcPol: 'um label plus',
    dcDarj: 'heavily urban',
    dcGrad: 'march',
    dcUlica: 'allocation thongs distorted',
  };

  let dMaikaRow;

  beforeEach(() => {
    cy.login(username, password);
  });

  beforeEach(() => {
    cy.intercept('GET', '/api/d-maika-rows+(?*|)').as('entitiesRequest');
    cy.intercept('POST', '/api/d-maika-rows').as('postEntityRequest');
    cy.intercept('DELETE', '/api/d-maika-rows/*').as('deleteEntityRequest');
  });

  afterEach(() => {
    if (dMaikaRow) {
      cy.authenticatedRequest({
        method: 'DELETE',
        url: `/api/d-maika-rows/${dMaikaRow.id}`,
      }).then(() => {
        dMaikaRow = undefined;
      });
    }
  });

  it('DMaikaRows menu should load DMaikaRows page', () => {
    cy.visit('/');
    cy.clickOnEntityMenuItem('d-maika-row');
    cy.wait('@entitiesRequest').then(({ response }) => {
      if (response?.body.length === 0) {
        cy.get(entityTableSelector).should('not.exist');
      } else {
        cy.get(entityTableSelector).should('exist');
      }
    });
    cy.getEntityHeading('DMaikaRow').should('exist');
    cy.url().should('match', dMaikaRowPageUrlPattern);
  });

  describe('DMaikaRow page', () => {
    describe('create button click', () => {
      beforeEach(() => {
        cy.visit(dMaikaRowPageUrl);
        cy.wait('@entitiesRequest');
      });

      it('should load create DMaikaRow page', () => {
        cy.get(entityCreateButtonSelector).click();
        cy.url().should('match', new RegExp('/d-maika-row/new$'));
        cy.getEntityCreateUpdateHeading('DMaikaRow');
        cy.get(entityCreateSaveButtonSelector).should('exist');
        cy.get(entityCreateCancelButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response?.statusCode).to.equal(200);
        });
        cy.url().should('match', dMaikaRowPageUrlPattern);
      });
    });

    describe('with existing value', () => {
      beforeEach(() => {
        cy.authenticatedRequest({
          method: 'POST',
          url: '/api/d-maika-rows',
          body: dMaikaRowSample,
        }).then(({ body }) => {
          dMaikaRow = body;

          cy.intercept(
            {
              method: 'GET',
              url: '/api/d-maika-rows+(?*|)',
              times: 1,
            },
            {
              statusCode: 200,
              headers: {
                link: '<http://localhost/api/d-maika-rows?page=0&size=20>; rel="last",<http://localhost/api/d-maika-rows?page=0&size=20>; rel="first"',
              },
              body: [dMaikaRow],
            },
          ).as('entitiesRequestInternal');
        });

        cy.visit(dMaikaRowPageUrl);

        cy.wait('@entitiesRequestInternal');
      });

      it('detail button click should load details DMaikaRow page', () => {
        cy.get(entityDetailsButtonSelector).first().click();
        cy.getEntityDetailsHeading('dMaikaRow');
        cy.get(entityDetailsBackButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response?.statusCode).to.equal(200);
        });
        cy.url().should('match', dMaikaRowPageUrlPattern);
      });

      it('edit button click should load edit DMaikaRow page and go back', () => {
        cy.get(entityEditButtonSelector).first().click();
        cy.getEntityCreateUpdateHeading('DMaikaRow');
        cy.get(entityCreateSaveButtonSelector).should('exist');
        cy.get(entityCreateCancelButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response?.statusCode).to.equal(200);
        });
        cy.url().should('match', dMaikaRowPageUrlPattern);
      });

      it('edit button click should load edit DMaikaRow page and save', () => {
        cy.get(entityEditButtonSelector).first().click();
        cy.getEntityCreateUpdateHeading('DMaikaRow');
        cy.get(entityCreateSaveButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response?.statusCode).to.equal(200);
        });
        cy.url().should('match', dMaikaRowPageUrlPattern);
      });

      it('last delete button click should delete instance of DMaikaRow', () => {
        cy.intercept('GET', '/api/d-maika-rows/*').as('dialogDeleteRequest');
        cy.get(entityDeleteButtonSelector).last().click();
        cy.wait('@dialogDeleteRequest');
        cy.getEntityDeleteDialogHeading('dMaikaRow').should('exist');
        cy.get(entityConfirmDeleteButtonSelector).click();
        cy.wait('@deleteEntityRequest').then(({ response }) => {
          expect(response?.statusCode).to.equal(204);
        });
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response?.statusCode).to.equal(200);
        });
        cy.url().should('match', dMaikaRowPageUrlPattern);

        dMaikaRow = undefined;
      });
    });
  });

  describe('new DMaikaRow page', () => {
    beforeEach(() => {
      cy.visit(`${dMaikaRowPageUrl}`);
      cy.get(entityCreateButtonSelector).click();
      cy.getEntityCreateUpdateHeading('DMaikaRow');
    });

    it('should create an instance of DMaikaRow', () => {
      cy.get(`[data-cy="dcFamil"]`).type('veto');
      cy.get(`[data-cy="dcFamil"]`).should('have.value', 'veto');

      cy.get(`[data-cy="dcImena"]`).type('unto');
      cy.get(`[data-cy="dcImena"]`).should('have.value', 'unto');

      cy.get(`[data-cy="dcPol"]`).type('diligent over');
      cy.get(`[data-cy="dcPol"]`).should('have.value', 'diligent over');

      cy.get(`[data-cy="dcDarj"]`).type('along');
      cy.get(`[data-cy="dcDarj"]`).should('have.value', 'along');

      cy.get(`[data-cy="dcGrad"]`).type('telescope blind yuck');
      cy.get(`[data-cy="dcGrad"]`).should('have.value', 'telescope blind yuck');

      cy.get(`[data-cy="dcUlica"]`).type('subtle below');
      cy.get(`[data-cy="dcUlica"]`).should('have.value', 'subtle below');

      cy.get(entityCreateSaveButtonSelector).click();

      cy.wait('@postEntityRequest').then(({ response }) => {
        expect(response?.statusCode).to.equal(201);
        dMaikaRow = response.body;
      });
      cy.wait('@entitiesRequest').then(({ response }) => {
        expect(response?.statusCode).to.equal(200);
      });
      cy.url().should('match', dMaikaRowPageUrlPattern);
    });
  });
});
