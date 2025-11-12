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

describe('DMsgheaderRow e2e test', () => {
  const dMsgheaderRowPageUrl = '/d-msgheader-row';
  const dMsgheaderRowPageUrlPattern = new RegExp('/d-msgheader-row(\\?.*)?$');
  const username = Cypress.env('E2E_USERNAME') ?? 'user';
  const password = Cypress.env('E2E_PASSWORD') ?? 'user';
  const dMsgheaderRowSample = {
    mhKscreated: 'creative yawningly vacantly',
    mhVfsrefno: 'comestible fit',
    mhUsera: 'easily almost',
    mhDatvav: 'over diligently around',
  };

  let dMsgheaderRow;

  beforeEach(() => {
    cy.login(username, password);
  });

  beforeEach(() => {
    cy.intercept('GET', '/api/d-msgheader-rows+(?*|)').as('entitiesRequest');
    cy.intercept('POST', '/api/d-msgheader-rows').as('postEntityRequest');
    cy.intercept('DELETE', '/api/d-msgheader-rows/*').as('deleteEntityRequest');
  });

  afterEach(() => {
    if (dMsgheaderRow) {
      cy.authenticatedRequest({
        method: 'DELETE',
        url: `/api/d-msgheader-rows/${dMsgheaderRow.id}`,
      }).then(() => {
        dMsgheaderRow = undefined;
      });
    }
  });

  it('DMsgheaderRows menu should load DMsgheaderRows page', () => {
    cy.visit('/');
    cy.clickOnEntityMenuItem('d-msgheader-row');
    cy.wait('@entitiesRequest').then(({ response }) => {
      if (response?.body.length === 0) {
        cy.get(entityTableSelector).should('not.exist');
      } else {
        cy.get(entityTableSelector).should('exist');
      }
    });
    cy.getEntityHeading('DMsgheaderRow').should('exist');
    cy.url().should('match', dMsgheaderRowPageUrlPattern);
  });

  describe('DMsgheaderRow page', () => {
    describe('create button click', () => {
      beforeEach(() => {
        cy.visit(dMsgheaderRowPageUrl);
        cy.wait('@entitiesRequest');
      });

      it('should load create DMsgheaderRow page', () => {
        cy.get(entityCreateButtonSelector).click();
        cy.url().should('match', new RegExp('/d-msgheader-row/new$'));
        cy.getEntityCreateUpdateHeading('DMsgheaderRow');
        cy.get(entityCreateSaveButtonSelector).should('exist');
        cy.get(entityCreateCancelButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response?.statusCode).to.equal(200);
        });
        cy.url().should('match', dMsgheaderRowPageUrlPattern);
      });
    });

    describe('with existing value', () => {
      beforeEach(() => {
        cy.authenticatedRequest({
          method: 'POST',
          url: '/api/d-msgheader-rows',
          body: dMsgheaderRowSample,
        }).then(({ body }) => {
          dMsgheaderRow = body;

          cy.intercept(
            {
              method: 'GET',
              url: '/api/d-msgheader-rows+(?*|)',
              times: 1,
            },
            {
              statusCode: 200,
              headers: {
                link: '<http://localhost/api/d-msgheader-rows?page=0&size=20>; rel="last",<http://localhost/api/d-msgheader-rows?page=0&size=20>; rel="first"',
              },
              body: [dMsgheaderRow],
            },
          ).as('entitiesRequestInternal');
        });

        cy.visit(dMsgheaderRowPageUrl);

        cy.wait('@entitiesRequestInternal');
      });

      it('detail button click should load details DMsgheaderRow page', () => {
        cy.get(entityDetailsButtonSelector).first().click();
        cy.getEntityDetailsHeading('dMsgheaderRow');
        cy.get(entityDetailsBackButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response?.statusCode).to.equal(200);
        });
        cy.url().should('match', dMsgheaderRowPageUrlPattern);
      });

      it('edit button click should load edit DMsgheaderRow page and go back', () => {
        cy.get(entityEditButtonSelector).first().click();
        cy.getEntityCreateUpdateHeading('DMsgheaderRow');
        cy.get(entityCreateSaveButtonSelector).should('exist');
        cy.get(entityCreateCancelButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response?.statusCode).to.equal(200);
        });
        cy.url().should('match', dMsgheaderRowPageUrlPattern);
      });

      it('edit button click should load edit DMsgheaderRow page and save', () => {
        cy.get(entityEditButtonSelector).first().click();
        cy.getEntityCreateUpdateHeading('DMsgheaderRow');
        cy.get(entityCreateSaveButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response?.statusCode).to.equal(200);
        });
        cy.url().should('match', dMsgheaderRowPageUrlPattern);
      });

      it('last delete button click should delete instance of DMsgheaderRow', () => {
        cy.intercept('GET', '/api/d-msgheader-rows/*').as('dialogDeleteRequest');
        cy.get(entityDeleteButtonSelector).last().click();
        cy.wait('@dialogDeleteRequest');
        cy.getEntityDeleteDialogHeading('dMsgheaderRow').should('exist');
        cy.get(entityConfirmDeleteButtonSelector).click();
        cy.wait('@deleteEntityRequest').then(({ response }) => {
          expect(response?.statusCode).to.equal(204);
        });
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response?.statusCode).to.equal(200);
        });
        cy.url().should('match', dMsgheaderRowPageUrlPattern);

        dMsgheaderRow = undefined;
      });
    });
  });

  describe('new DMsgheaderRow page', () => {
    beforeEach(() => {
      cy.visit(`${dMsgheaderRowPageUrl}`);
      cy.get(entityCreateButtonSelector).click();
      cy.getEntityCreateUpdateHeading('DMsgheaderRow');
    });

    it('should create an instance of DMsgheaderRow', () => {
      cy.get(`[data-cy="mhKscreated"]`).type('intensely instruction unless');
      cy.get(`[data-cy="mhKscreated"]`).should('have.value', 'intensely instruction unless');

      cy.get(`[data-cy="mhRegnom"]`).type('6234');
      cy.get(`[data-cy="mhRegnom"]`).should('have.value', '6234');

      cy.get(`[data-cy="mhVfsrefno"]`).type('pish intently spew');
      cy.get(`[data-cy="mhVfsrefno"]`).should('have.value', 'pish intently spew');

      cy.get(`[data-cy="mhUsera"]`).type('physically shiny great');
      cy.get(`[data-cy="mhUsera"]`).should('have.value', 'physically shiny great');

      cy.get(`[data-cy="mhDatvav"]`).type('yum');
      cy.get(`[data-cy="mhDatvav"]`).should('have.value', 'yum');

      cy.get(entityCreateSaveButtonSelector).click();

      cy.wait('@postEntityRequest').then(({ response }) => {
        expect(response?.statusCode).to.equal(201);
        dMsgheaderRow = response.body;
      });
      cy.wait('@entitiesRequest').then(({ response }) => {
        expect(response?.statusCode).to.equal(200);
      });
      cy.url().should('match', dMsgheaderRowPageUrlPattern);
    });
  });
});
