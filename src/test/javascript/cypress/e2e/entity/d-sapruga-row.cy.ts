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

describe('DSaprugaRow e2e test', () => {
  const dSaprugaRowPageUrl = '/d-sapruga-row';
  const dSaprugaRowPageUrlPattern = new RegExp('/d-sapruga-row(\\?.*)?$');
  const username = Cypress.env('E2E_USERNAME') ?? 'user';
  const password = Cypress.env('E2E_PASSWORD') ?? 'user';
  const dSaprugaRowSample = { spMrjdarj: 'mummify into' };

  let dSaprugaRow;

  beforeEach(() => {
    cy.login(username, password);
  });

  beforeEach(() => {
    cy.intercept('GET', '/api/d-sapruga-rows+(?*|)').as('entitiesRequest');
    cy.intercept('POST', '/api/d-sapruga-rows').as('postEntityRequest');
    cy.intercept('DELETE', '/api/d-sapruga-rows/*').as('deleteEntityRequest');
  });

  afterEach(() => {
    if (dSaprugaRow) {
      cy.authenticatedRequest({
        method: 'DELETE',
        url: `/api/d-sapruga-rows/${dSaprugaRow.id}`,
      }).then(() => {
        dSaprugaRow = undefined;
      });
    }
  });

  it('DSaprugaRows menu should load DSaprugaRows page', () => {
    cy.visit('/');
    cy.clickOnEntityMenuItem('d-sapruga-row');
    cy.wait('@entitiesRequest').then(({ response }) => {
      if (response?.body.length === 0) {
        cy.get(entityTableSelector).should('not.exist');
      } else {
        cy.get(entityTableSelector).should('exist');
      }
    });
    cy.getEntityHeading('DSaprugaRow').should('exist');
    cy.url().should('match', dSaprugaRowPageUrlPattern);
  });

  describe('DSaprugaRow page', () => {
    describe('create button click', () => {
      beforeEach(() => {
        cy.visit(dSaprugaRowPageUrl);
        cy.wait('@entitiesRequest');
      });

      it('should load create DSaprugaRow page', () => {
        cy.get(entityCreateButtonSelector).click();
        cy.url().should('match', new RegExp('/d-sapruga-row/new$'));
        cy.getEntityCreateUpdateHeading('DSaprugaRow');
        cy.get(entityCreateSaveButtonSelector).should('exist');
        cy.get(entityCreateCancelButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response?.statusCode).to.equal(200);
        });
        cy.url().should('match', dSaprugaRowPageUrlPattern);
      });
    });

    describe('with existing value', () => {
      beforeEach(() => {
        cy.authenticatedRequest({
          method: 'POST',
          url: '/api/d-sapruga-rows',
          body: dSaprugaRowSample,
        }).then(({ body }) => {
          dSaprugaRow = body;

          cy.intercept(
            {
              method: 'GET',
              url: '/api/d-sapruga-rows+(?*|)',
              times: 1,
            },
            {
              statusCode: 200,
              headers: {
                link: '<http://localhost/api/d-sapruga-rows?page=0&size=20>; rel="last",<http://localhost/api/d-sapruga-rows?page=0&size=20>; rel="first"',
              },
              body: [dSaprugaRow],
            },
          ).as('entitiesRequestInternal');
        });

        cy.visit(dSaprugaRowPageUrl);

        cy.wait('@entitiesRequestInternal');
      });

      it('detail button click should load details DSaprugaRow page', () => {
        cy.get(entityDetailsButtonSelector).first().click();
        cy.getEntityDetailsHeading('dSaprugaRow');
        cy.get(entityDetailsBackButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response?.statusCode).to.equal(200);
        });
        cy.url().should('match', dSaprugaRowPageUrlPattern);
      });

      it('edit button click should load edit DSaprugaRow page and go back', () => {
        cy.get(entityEditButtonSelector).first().click();
        cy.getEntityCreateUpdateHeading('DSaprugaRow');
        cy.get(entityCreateSaveButtonSelector).should('exist');
        cy.get(entityCreateCancelButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response?.statusCode).to.equal(200);
        });
        cy.url().should('match', dSaprugaRowPageUrlPattern);
      });

      it('edit button click should load edit DSaprugaRow page and save', () => {
        cy.get(entityEditButtonSelector).first().click();
        cy.getEntityCreateUpdateHeading('DSaprugaRow');
        cy.get(entityCreateSaveButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response?.statusCode).to.equal(200);
        });
        cy.url().should('match', dSaprugaRowPageUrlPattern);
      });

      it('last delete button click should delete instance of DSaprugaRow', () => {
        cy.intercept('GET', '/api/d-sapruga-rows/*').as('dialogDeleteRequest');
        cy.get(entityDeleteButtonSelector).last().click();
        cy.wait('@dialogDeleteRequest');
        cy.getEntityDeleteDialogHeading('dSaprugaRow').should('exist');
        cy.get(entityConfirmDeleteButtonSelector).click();
        cy.wait('@deleteEntityRequest').then(({ response }) => {
          expect(response?.statusCode).to.equal(204);
        });
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response?.statusCode).to.equal(200);
        });
        cy.url().should('match', dSaprugaRowPageUrlPattern);

        dSaprugaRow = undefined;
      });
    });
  });

  describe('new DSaprugaRow page', () => {
    beforeEach(() => {
      cy.visit(`${dSaprugaRowPageUrl}`);
      cy.get(entityCreateButtonSelector).click();
      cy.getEntityCreateUpdateHeading('DSaprugaRow');
    });

    it('should create an instance of DSaprugaRow', () => {
      cy.get(`[data-cy="spMrjdarj"]`).type('phew though');
      cy.get(`[data-cy="spMrjdarj"]`).should('have.value', 'phew though');

      cy.get(entityCreateSaveButtonSelector).click();

      cy.wait('@postEntityRequest').then(({ response }) => {
        expect(response?.statusCode).to.equal(201);
        dSaprugaRow = response.body;
      });
      cy.wait('@entitiesRequest').then(({ response }) => {
        expect(response?.statusCode).to.equal(200);
      });
      cy.url().should('match', dSaprugaRowPageUrlPattern);
    });
  });
});
