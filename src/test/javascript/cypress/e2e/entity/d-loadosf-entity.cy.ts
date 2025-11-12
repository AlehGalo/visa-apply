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

describe('DLoadosfEntity e2e test', () => {
  const dLoadosfEntityPageUrl = '/d-loadosf-entity';
  const dLoadosfEntityPageUrlPattern = new RegExp('/d-loadosf-entity(\\?.*)?$');
  const username = Cypress.env('E2E_USERNAME') ?? 'user';
  const password = Cypress.env('E2E_PASSWORD') ?? 'user';
  const dLoadosfEntitySample = {};

  let dLoadosfEntity;

  beforeEach(() => {
    cy.login(username, password);
  });

  beforeEach(() => {
    cy.intercept('GET', '/api/d-loadosf-entities+(?*|)').as('entitiesRequest');
    cy.intercept('POST', '/api/d-loadosf-entities').as('postEntityRequest');
    cy.intercept('DELETE', '/api/d-loadosf-entities/*').as('deleteEntityRequest');
  });

  afterEach(() => {
    if (dLoadosfEntity) {
      cy.authenticatedRequest({
        method: 'DELETE',
        url: `/api/d-loadosf-entities/${dLoadosfEntity.id}`,
      }).then(() => {
        dLoadosfEntity = undefined;
      });
    }
  });

  it('DLoadosfEntities menu should load DLoadosfEntities page', () => {
    cy.visit('/');
    cy.clickOnEntityMenuItem('d-loadosf-entity');
    cy.wait('@entitiesRequest').then(({ response }) => {
      if (response?.body.length === 0) {
        cy.get(entityTableSelector).should('not.exist');
      } else {
        cy.get(entityTableSelector).should('exist');
      }
    });
    cy.getEntityHeading('DLoadosfEntity').should('exist');
    cy.url().should('match', dLoadosfEntityPageUrlPattern);
  });

  describe('DLoadosfEntity page', () => {
    describe('create button click', () => {
      beforeEach(() => {
        cy.visit(dLoadosfEntityPageUrl);
        cy.wait('@entitiesRequest');
      });

      it('should load create DLoadosfEntity page', () => {
        cy.get(entityCreateButtonSelector).click();
        cy.url().should('match', new RegExp('/d-loadosf-entity/new$'));
        cy.getEntityCreateUpdateHeading('DLoadosfEntity');
        cy.get(entityCreateSaveButtonSelector).should('exist');
        cy.get(entityCreateCancelButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response?.statusCode).to.equal(200);
        });
        cy.url().should('match', dLoadosfEntityPageUrlPattern);
      });
    });

    describe('with existing value', () => {
      beforeEach(() => {
        cy.authenticatedRequest({
          method: 'POST',
          url: '/api/d-loadosf-entities',
          body: dLoadosfEntitySample,
        }).then(({ body }) => {
          dLoadosfEntity = body;

          cy.intercept(
            {
              method: 'GET',
              url: '/api/d-loadosf-entities+(?*|)',
              times: 1,
            },
            {
              statusCode: 200,
              headers: {
                link: '<http://localhost/api/d-loadosf-entities?page=0&size=20>; rel="last",<http://localhost/api/d-loadosf-entities?page=0&size=20>; rel="first"',
              },
              body: [dLoadosfEntity],
            },
          ).as('entitiesRequestInternal');
        });

        cy.visit(dLoadosfEntityPageUrl);

        cy.wait('@entitiesRequestInternal');
      });

      it('detail button click should load details DLoadosfEntity page', () => {
        cy.get(entityDetailsButtonSelector).first().click();
        cy.getEntityDetailsHeading('dLoadosfEntity');
        cy.get(entityDetailsBackButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response?.statusCode).to.equal(200);
        });
        cy.url().should('match', dLoadosfEntityPageUrlPattern);
      });

      it('edit button click should load edit DLoadosfEntity page and go back', () => {
        cy.get(entityEditButtonSelector).first().click();
        cy.getEntityCreateUpdateHeading('DLoadosfEntity');
        cy.get(entityCreateSaveButtonSelector).should('exist');
        cy.get(entityCreateCancelButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response?.statusCode).to.equal(200);
        });
        cy.url().should('match', dLoadosfEntityPageUrlPattern);
      });

      it('edit button click should load edit DLoadosfEntity page and save', () => {
        cy.get(entityEditButtonSelector).first().click();
        cy.getEntityCreateUpdateHeading('DLoadosfEntity');
        cy.get(entityCreateSaveButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response?.statusCode).to.equal(200);
        });
        cy.url().should('match', dLoadosfEntityPageUrlPattern);
      });

      it('last delete button click should delete instance of DLoadosfEntity', () => {
        cy.intercept('GET', '/api/d-loadosf-entities/*').as('dialogDeleteRequest');
        cy.get(entityDeleteButtonSelector).last().click();
        cy.wait('@dialogDeleteRequest');
        cy.getEntityDeleteDialogHeading('dLoadosfEntity').should('exist');
        cy.get(entityConfirmDeleteButtonSelector).click();
        cy.wait('@deleteEntityRequest').then(({ response }) => {
          expect(response?.statusCode).to.equal(204);
        });
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response?.statusCode).to.equal(200);
        });
        cy.url().should('match', dLoadosfEntityPageUrlPattern);

        dLoadosfEntity = undefined;
      });
    });
  });

  describe('new DLoadosfEntity page', () => {
    beforeEach(() => {
      cy.visit(`${dLoadosfEntityPageUrl}`);
      cy.get(entityCreateButtonSelector).click();
      cy.getEntityCreateUpdateHeading('DLoadosfEntity');
    });

    it('should create an instance of DLoadosfEntity', () => {
      cy.get(`[data-cy="version"]`).type('23320.6');
      cy.get(`[data-cy="version"]`).should('have.value', '23320.6');

      cy.get(entityCreateSaveButtonSelector).click();

      cy.wait('@postEntityRequest').then(({ response }) => {
        expect(response?.statusCode).to.equal(201);
        dLoadosfEntity = response.body;
      });
      cy.wait('@entitiesRequest').then(({ response }) => {
        expect(response?.statusCode).to.equal(200);
      });
      cy.url().should('match', dLoadosfEntityPageUrlPattern);
    });
  });
});
