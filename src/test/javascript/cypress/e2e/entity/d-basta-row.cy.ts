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

describe('DBastaRow e2e test', () => {
  const dBastaRowPageUrl = '/d-basta-row';
  const dBastaRowPageUrlPattern = new RegExp('/d-basta-row(\\?.*)?$');
  const username = Cypress.env('E2E_USERNAME') ?? 'user';
  const password = Cypress.env('E2E_PASSWORD') ?? 'user';
  const dBastaRowSample = {
    dcFamil: 'besides until',
    dcImena: 'fooey unto cassava',
    dcPol: 'noisily',
    dcGrad: 'dreamily yippee',
    dcUlica: 'blue blindly animated',
  };

  let dBastaRow;

  beforeEach(() => {
    cy.login(username, password);
  });

  beforeEach(() => {
    cy.intercept('GET', '/api/d-basta-rows+(?*|)').as('entitiesRequest');
    cy.intercept('POST', '/api/d-basta-rows').as('postEntityRequest');
    cy.intercept('DELETE', '/api/d-basta-rows/*').as('deleteEntityRequest');
  });

  afterEach(() => {
    if (dBastaRow) {
      cy.authenticatedRequest({
        method: 'DELETE',
        url: `/api/d-basta-rows/${dBastaRow.id}`,
      }).then(() => {
        dBastaRow = undefined;
      });
    }
  });

  it('DBastaRows menu should load DBastaRows page', () => {
    cy.visit('/');
    cy.clickOnEntityMenuItem('d-basta-row');
    cy.wait('@entitiesRequest').then(({ response }) => {
      if (response?.body.length === 0) {
        cy.get(entityTableSelector).should('not.exist');
      } else {
        cy.get(entityTableSelector).should('exist');
      }
    });
    cy.getEntityHeading('DBastaRow').should('exist');
    cy.url().should('match', dBastaRowPageUrlPattern);
  });

  describe('DBastaRow page', () => {
    describe('create button click', () => {
      beforeEach(() => {
        cy.visit(dBastaRowPageUrl);
        cy.wait('@entitiesRequest');
      });

      it('should load create DBastaRow page', () => {
        cy.get(entityCreateButtonSelector).click();
        cy.url().should('match', new RegExp('/d-basta-row/new$'));
        cy.getEntityCreateUpdateHeading('DBastaRow');
        cy.get(entityCreateSaveButtonSelector).should('exist');
        cy.get(entityCreateCancelButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response?.statusCode).to.equal(200);
        });
        cy.url().should('match', dBastaRowPageUrlPattern);
      });
    });

    describe('with existing value', () => {
      beforeEach(() => {
        cy.authenticatedRequest({
          method: 'POST',
          url: '/api/d-basta-rows',
          body: dBastaRowSample,
        }).then(({ body }) => {
          dBastaRow = body;

          cy.intercept(
            {
              method: 'GET',
              url: '/api/d-basta-rows+(?*|)',
              times: 1,
            },
            {
              statusCode: 200,
              headers: {
                link: '<http://localhost/api/d-basta-rows?page=0&size=20>; rel="last",<http://localhost/api/d-basta-rows?page=0&size=20>; rel="first"',
              },
              body: [dBastaRow],
            },
          ).as('entitiesRequestInternal');
        });

        cy.visit(dBastaRowPageUrl);

        cy.wait('@entitiesRequestInternal');
      });

      it('detail button click should load details DBastaRow page', () => {
        cy.get(entityDetailsButtonSelector).first().click();
        cy.getEntityDetailsHeading('dBastaRow');
        cy.get(entityDetailsBackButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response?.statusCode).to.equal(200);
        });
        cy.url().should('match', dBastaRowPageUrlPattern);
      });

      it('edit button click should load edit DBastaRow page and go back', () => {
        cy.get(entityEditButtonSelector).first().click();
        cy.getEntityCreateUpdateHeading('DBastaRow');
        cy.get(entityCreateSaveButtonSelector).should('exist');
        cy.get(entityCreateCancelButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response?.statusCode).to.equal(200);
        });
        cy.url().should('match', dBastaRowPageUrlPattern);
      });

      it('edit button click should load edit DBastaRow page and save', () => {
        cy.get(entityEditButtonSelector).first().click();
        cy.getEntityCreateUpdateHeading('DBastaRow');
        cy.get(entityCreateSaveButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response?.statusCode).to.equal(200);
        });
        cy.url().should('match', dBastaRowPageUrlPattern);
      });

      it('last delete button click should delete instance of DBastaRow', () => {
        cy.intercept('GET', '/api/d-basta-rows/*').as('dialogDeleteRequest');
        cy.get(entityDeleteButtonSelector).last().click();
        cy.wait('@dialogDeleteRequest');
        cy.getEntityDeleteDialogHeading('dBastaRow').should('exist');
        cy.get(entityConfirmDeleteButtonSelector).click();
        cy.wait('@deleteEntityRequest').then(({ response }) => {
          expect(response?.statusCode).to.equal(204);
        });
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response?.statusCode).to.equal(200);
        });
        cy.url().should('match', dBastaRowPageUrlPattern);

        dBastaRow = undefined;
      });
    });
  });

  describe('new DBastaRow page', () => {
    beforeEach(() => {
      cy.visit(`${dBastaRowPageUrl}`);
      cy.get(entityCreateButtonSelector).click();
      cy.getEntityCreateUpdateHeading('DBastaRow');
    });

    it('should create an instance of DBastaRow', () => {
      cy.get(`[data-cy="dcFamil"]`).type('less fort');
      cy.get(`[data-cy="dcFamil"]`).should('have.value', 'less fort');

      cy.get(`[data-cy="dcImena"]`).type('brr yahoo');
      cy.get(`[data-cy="dcImena"]`).should('have.value', 'brr yahoo');

      cy.get(`[data-cy="dcPol"]`).type('cork blushing');
      cy.get(`[data-cy="dcPol"]`).should('have.value', 'cork blushing');

      cy.get(`[data-cy="dcGrad"]`).type('confute till till');
      cy.get(`[data-cy="dcGrad"]`).should('have.value', 'confute till till');

      cy.get(`[data-cy="dcUlica"]`).type('consequently when leading');
      cy.get(`[data-cy="dcUlica"]`).should('have.value', 'consequently when leading');

      cy.get(entityCreateSaveButtonSelector).click();

      cy.wait('@postEntityRequest').then(({ response }) => {
        expect(response?.statusCode).to.equal(201);
        dBastaRow = response.body;
      });
      cy.wait('@entitiesRequest').then(({ response }) => {
        expect(response?.statusCode).to.equal(200);
      });
      cy.url().should('match', dBastaRowPageUrlPattern);
    });
  });
});
