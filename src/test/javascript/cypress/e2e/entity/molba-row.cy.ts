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

describe('MolbaRow e2e test', () => {
  const molbaRowPageUrl = '/molba-row';
  const molbaRowPageUrlPattern = new RegExp('/molba-row(\\?.*)?$');
  const username = Cypress.env('E2E_USERNAME') ?? 'user';
  const password = Cypress.env('E2E_PASSWORD') ?? 'user';
  const molbaRowSample = {
    datVli: '2025-10-30',
    datIzl: '2025-10-29',
    vidvis: 'ugh following',
    vidus: 'thoughtfully around gladly',
    valvis: 'phew',
    molDatVav: '2025-10-30T04:27:15.686Z',
    gratis: 'quart',
    imavisa: 'obvious kosher',
    cenacurr: 'er',
    maindest: 'sparkling resort who',
    maindestnm: 'drat when',
    gkppDarj: 'while own fondly',
    gkppText: 'separate',
    textIni: 'republican glaring',
  };

  let molbaRow;

  beforeEach(() => {
    cy.login(username, password);
  });

  beforeEach(() => {
    cy.intercept('GET', '/api/molba-rows+(?*|)').as('entitiesRequest');
    cy.intercept('POST', '/api/molba-rows').as('postEntityRequest');
    cy.intercept('DELETE', '/api/molba-rows/*').as('deleteEntityRequest');
  });

  afterEach(() => {
    if (molbaRow) {
      cy.authenticatedRequest({
        method: 'DELETE',
        url: `/api/molba-rows/${molbaRow.id}`,
      }).then(() => {
        molbaRow = undefined;
      });
    }
  });

  it('MolbaRows menu should load MolbaRows page', () => {
    cy.visit('/');
    cy.clickOnEntityMenuItem('molba-row');
    cy.wait('@entitiesRequest').then(({ response }) => {
      if (response?.body.length === 0) {
        cy.get(entityTableSelector).should('not.exist');
      } else {
        cy.get(entityTableSelector).should('exist');
      }
    });
    cy.getEntityHeading('MolbaRow').should('exist');
    cy.url().should('match', molbaRowPageUrlPattern);
  });

  describe('MolbaRow page', () => {
    describe('create button click', () => {
      beforeEach(() => {
        cy.visit(molbaRowPageUrl);
        cy.wait('@entitiesRequest');
      });

      it('should load create MolbaRow page', () => {
        cy.get(entityCreateButtonSelector).click();
        cy.url().should('match', new RegExp('/molba-row/new$'));
        cy.getEntityCreateUpdateHeading('MolbaRow');
        cy.get(entityCreateSaveButtonSelector).should('exist');
        cy.get(entityCreateCancelButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response?.statusCode).to.equal(200);
        });
        cy.url().should('match', molbaRowPageUrlPattern);
      });
    });

    describe('with existing value', () => {
      beforeEach(() => {
        cy.authenticatedRequest({
          method: 'POST',
          url: '/api/molba-rows',
          body: molbaRowSample,
        }).then(({ body }) => {
          molbaRow = body;

          cy.intercept(
            {
              method: 'GET',
              url: '/api/molba-rows+(?*|)',
              times: 1,
            },
            {
              statusCode: 200,
              headers: {
                link: '<http://localhost/api/molba-rows?page=0&size=20>; rel="last",<http://localhost/api/molba-rows?page=0&size=20>; rel="first"',
              },
              body: [molbaRow],
            },
          ).as('entitiesRequestInternal');
        });

        cy.visit(molbaRowPageUrl);

        cy.wait('@entitiesRequestInternal');
      });

      it('detail button click should load details MolbaRow page', () => {
        cy.get(entityDetailsButtonSelector).first().click();
        cy.getEntityDetailsHeading('molbaRow');
        cy.get(entityDetailsBackButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response?.statusCode).to.equal(200);
        });
        cy.url().should('match', molbaRowPageUrlPattern);
      });

      it('edit button click should load edit MolbaRow page and go back', () => {
        cy.get(entityEditButtonSelector).first().click();
        cy.getEntityCreateUpdateHeading('MolbaRow');
        cy.get(entityCreateSaveButtonSelector).should('exist');
        cy.get(entityCreateCancelButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response?.statusCode).to.equal(200);
        });
        cy.url().should('match', molbaRowPageUrlPattern);
      });

      it('edit button click should load edit MolbaRow page and save', () => {
        cy.get(entityEditButtonSelector).first().click();
        cy.getEntityCreateUpdateHeading('MolbaRow');
        cy.get(entityCreateSaveButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response?.statusCode).to.equal(200);
        });
        cy.url().should('match', molbaRowPageUrlPattern);
      });

      it('last delete button click should delete instance of MolbaRow', () => {
        cy.intercept('GET', '/api/molba-rows/*').as('dialogDeleteRequest');
        cy.get(entityDeleteButtonSelector).last().click();
        cy.wait('@dialogDeleteRequest');
        cy.getEntityDeleteDialogHeading('molbaRow').should('exist');
        cy.get(entityConfirmDeleteButtonSelector).click();
        cy.wait('@deleteEntityRequest').then(({ response }) => {
          expect(response?.statusCode).to.equal(204);
        });
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response?.statusCode).to.equal(200);
        });
        cy.url().should('match', molbaRowPageUrlPattern);

        molbaRow = undefined;
      });
    });
  });

  describe('new MolbaRow page', () => {
    beforeEach(() => {
      cy.visit(`${molbaRowPageUrl}`);
      cy.get(entityCreateButtonSelector).click();
      cy.getEntityCreateUpdateHeading('MolbaRow');
    });

    it('should create an instance of MolbaRow', () => {
      cy.get(`[data-cy="datVli"]`).type('2025-10-29');
      cy.get(`[data-cy="datVli"]`).blur();
      cy.get(`[data-cy="datVli"]`).should('have.value', '2025-10-29');

      cy.get(`[data-cy="datIzl"]`).type('2025-10-29');
      cy.get(`[data-cy="datIzl"]`).blur();
      cy.get(`[data-cy="datIzl"]`).should('have.value', '2025-10-29');

      cy.get(`[data-cy="vidvis"]`).type('deer winding');
      cy.get(`[data-cy="vidvis"]`).should('have.value', 'deer winding');

      cy.get(`[data-cy="brvl"]`).type('13905');
      cy.get(`[data-cy="brvl"]`).should('have.value', '13905');

      cy.get(`[data-cy="vidus"]`).type('tray splash via');
      cy.get(`[data-cy="vidus"]`).should('have.value', 'tray splash via');

      cy.get(`[data-cy="valvis"]`).type('which SUV consequently');
      cy.get(`[data-cy="valvis"]`).should('have.value', 'which SUV consequently');

      cy.get(`[data-cy="brdni"]`).type('22807');
      cy.get(`[data-cy="brdni"]`).should('have.value', '22807');

      cy.get(`[data-cy="cel"]`).type('3372');
      cy.get(`[data-cy="cel"]`).should('have.value', '3372');

      cy.get(`[data-cy="molDatVav"]`).type('2025-10-29T15:46');
      cy.get(`[data-cy="molDatVav"]`).blur();
      cy.get(`[data-cy="molDatVav"]`).should('have.value', '2025-10-29T15:46');

      cy.get(`[data-cy="gratis"]`).type('apt');
      cy.get(`[data-cy="gratis"]`).should('have.value', 'apt');

      cy.get(`[data-cy="imavisa"]`).type('both so efface');
      cy.get(`[data-cy="imavisa"]`).should('have.value', 'both so efface');

      cy.get(`[data-cy="cenamol"]`).type('1890');
      cy.get(`[data-cy="cenamol"]`).should('have.value', '1890');

      cy.get(`[data-cy="cenacurr"]`).type('boohoo seemingly hierarchy');
      cy.get(`[data-cy="cenacurr"]`).should('have.value', 'boohoo seemingly hierarchy');

      cy.get(`[data-cy="maindest"]`).type('which transom');
      cy.get(`[data-cy="maindest"]`).should('have.value', 'which transom');

      cy.get(`[data-cy="maindestnm"]`).type('bah catalyst');
      cy.get(`[data-cy="maindestnm"]`).should('have.value', 'bah catalyst');

      cy.get(`[data-cy="gkppDarj"]`).type('or');
      cy.get(`[data-cy="gkppDarj"]`).should('have.value', 'or');

      cy.get(`[data-cy="gkppText"]`).type('um instead lid');
      cy.get(`[data-cy="gkppText"]`).should('have.value', 'um instead lid');

      cy.get(`[data-cy="textIni"]`).type('in far-off');
      cy.get(`[data-cy="textIni"]`).should('have.value', 'in far-off');

      cy.get(entityCreateSaveButtonSelector).click();

      cy.wait('@postEntityRequest').then(({ response }) => {
        expect(response?.statusCode).to.equal(201);
        molbaRow = response.body;
      });
      cy.wait('@entitiesRequest').then(({ response }) => {
        expect(response?.statusCode).to.equal(200);
      });
      cy.url().should('match', molbaRowPageUrlPattern);
    });
  });
});
