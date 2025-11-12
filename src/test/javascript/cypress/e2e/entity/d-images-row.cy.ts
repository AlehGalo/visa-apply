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

describe('DImagesRow e2e test', () => {
  const dImagesRowPageUrl = '/d-images-row';
  const dImagesRowPageUrlPattern = new RegExp('/d-images-row(\\?.*)?$');
  const username = Cypress.env('E2E_USERNAME') ?? 'user';
  const password = Cypress.env('E2E_PASSWORD') ?? 'user';
  const dImagesRowSample = { imImage: 'come egg' };

  let dImagesRow;

  beforeEach(() => {
    cy.login(username, password);
  });

  beforeEach(() => {
    cy.intercept('GET', '/api/d-images-rows+(?*|)').as('entitiesRequest');
    cy.intercept('POST', '/api/d-images-rows').as('postEntityRequest');
    cy.intercept('DELETE', '/api/d-images-rows/*').as('deleteEntityRequest');
  });

  afterEach(() => {
    if (dImagesRow) {
      cy.authenticatedRequest({
        method: 'DELETE',
        url: `/api/d-images-rows/${dImagesRow.id}`,
      }).then(() => {
        dImagesRow = undefined;
      });
    }
  });

  it('DImagesRows menu should load DImagesRows page', () => {
    cy.visit('/');
    cy.clickOnEntityMenuItem('d-images-row');
    cy.wait('@entitiesRequest').then(({ response }) => {
      if (response?.body.length === 0) {
        cy.get(entityTableSelector).should('not.exist');
      } else {
        cy.get(entityTableSelector).should('exist');
      }
    });
    cy.getEntityHeading('DImagesRow').should('exist');
    cy.url().should('match', dImagesRowPageUrlPattern);
  });

  describe('DImagesRow page', () => {
    describe('create button click', () => {
      beforeEach(() => {
        cy.visit(dImagesRowPageUrl);
        cy.wait('@entitiesRequest');
      });

      it('should load create DImagesRow page', () => {
        cy.get(entityCreateButtonSelector).click();
        cy.url().should('match', new RegExp('/d-images-row/new$'));
        cy.getEntityCreateUpdateHeading('DImagesRow');
        cy.get(entityCreateSaveButtonSelector).should('exist');
        cy.get(entityCreateCancelButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response?.statusCode).to.equal(200);
        });
        cy.url().should('match', dImagesRowPageUrlPattern);
      });
    });

    describe('with existing value', () => {
      beforeEach(() => {
        cy.authenticatedRequest({
          method: 'POST',
          url: '/api/d-images-rows',
          body: dImagesRowSample,
        }).then(({ body }) => {
          dImagesRow = body;

          cy.intercept(
            {
              method: 'GET',
              url: '/api/d-images-rows+(?*|)',
              times: 1,
            },
            {
              statusCode: 200,
              headers: {
                link: '<http://localhost/api/d-images-rows?page=0&size=20>; rel="last",<http://localhost/api/d-images-rows?page=0&size=20>; rel="first"',
              },
              body: [dImagesRow],
            },
          ).as('entitiesRequestInternal');
        });

        cy.visit(dImagesRowPageUrl);

        cy.wait('@entitiesRequestInternal');
      });

      it('detail button click should load details DImagesRow page', () => {
        cy.get(entityDetailsButtonSelector).first().click();
        cy.getEntityDetailsHeading('dImagesRow');
        cy.get(entityDetailsBackButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response?.statusCode).to.equal(200);
        });
        cy.url().should('match', dImagesRowPageUrlPattern);
      });

      it('edit button click should load edit DImagesRow page and go back', () => {
        cy.get(entityEditButtonSelector).first().click();
        cy.getEntityCreateUpdateHeading('DImagesRow');
        cy.get(entityCreateSaveButtonSelector).should('exist');
        cy.get(entityCreateCancelButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response?.statusCode).to.equal(200);
        });
        cy.url().should('match', dImagesRowPageUrlPattern);
      });

      it('edit button click should load edit DImagesRow page and save', () => {
        cy.get(entityEditButtonSelector).first().click();
        cy.getEntityCreateUpdateHeading('DImagesRow');
        cy.get(entityCreateSaveButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response?.statusCode).to.equal(200);
        });
        cy.url().should('match', dImagesRowPageUrlPattern);
      });

      it('last delete button click should delete instance of DImagesRow', () => {
        cy.intercept('GET', '/api/d-images-rows/*').as('dialogDeleteRequest');
        cy.get(entityDeleteButtonSelector).last().click();
        cy.wait('@dialogDeleteRequest');
        cy.getEntityDeleteDialogHeading('dImagesRow').should('exist');
        cy.get(entityConfirmDeleteButtonSelector).click();
        cy.wait('@deleteEntityRequest').then(({ response }) => {
          expect(response?.statusCode).to.equal(204);
        });
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response?.statusCode).to.equal(200);
        });
        cy.url().should('match', dImagesRowPageUrlPattern);

        dImagesRow = undefined;
      });
    });
  });

  describe('new DImagesRow page', () => {
    beforeEach(() => {
      cy.visit(`${dImagesRowPageUrl}`);
      cy.get(entityCreateButtonSelector).click();
      cy.getEntityCreateUpdateHeading('DImagesRow');
    });

    it('should create an instance of DImagesRow', () => {
      cy.get(`[data-cy="imDevicetype"]`).type('16696');
      cy.get(`[data-cy="imDevicetype"]`).should('have.value', '16696');

      cy.get(`[data-cy="imWidth"]`).type('12177');
      cy.get(`[data-cy="imWidth"]`).should('have.value', '12177');

      cy.get(`[data-cy="imHeight"]`).type('26966');
      cy.get(`[data-cy="imHeight"]`).should('have.value', '26966');

      cy.get(`[data-cy="imImglen"]`).type('5991');
      cy.get(`[data-cy="imImglen"]`).should('have.value', '5991');

      cy.get(`[data-cy="imImage"]`).type('when trench');
      cy.get(`[data-cy="imImage"]`).should('have.value', 'when trench');

      cy.get(entityCreateSaveButtonSelector).click();

      cy.wait('@postEntityRequest').then(({ response }) => {
        expect(response?.statusCode).to.equal(201);
        dImagesRow = response.body;
      });
      cy.wait('@entitiesRequest').then(({ response }) => {
        expect(response?.statusCode).to.equal(200);
      });
      cy.url().should('match', dImagesRowPageUrlPattern);
    });
  });
});
