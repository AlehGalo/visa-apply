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

describe('DDomakinRow e2e test', () => {
  const dDomakinRowPageUrl = '/d-domakin-row';
  const dDomakinRowPageUrlPattern = new RegExp('/d-domakin-row(\\?.*)?$');
  const username = Cypress.env('E2E_USERNAME') ?? 'user';
  const password = Cypress.env('E2E_PASSWORD') ?? 'user';
  const dDomakinRowSample = {
    dmVid: 'sidetrack',
    nomPok: 'lasting',
    domGraj: 'finally ferociously subdued',
    domFamil: 'briefly onto',
    domIme: 'except after',
    domDarj: 'gullible courageously via',
    domAdres: 'bemuse',
    vedDarj: 'so scarcely',
    vedNm: 'shiny misfire',
  };

  let dDomakinRow;

  beforeEach(() => {
    cy.login(username, password);
  });

  beforeEach(() => {
    cy.intercept('GET', '/api/d-domakin-rows+(?*|)').as('entitiesRequest');
    cy.intercept('POST', '/api/d-domakin-rows').as('postEntityRequest');
    cy.intercept('DELETE', '/api/d-domakin-rows/*').as('deleteEntityRequest');
  });

  afterEach(() => {
    if (dDomakinRow) {
      cy.authenticatedRequest({
        method: 'DELETE',
        url: `/api/d-domakin-rows/${dDomakinRow.id}`,
      }).then(() => {
        dDomakinRow = undefined;
      });
    }
  });

  it('DDomakinRows menu should load DDomakinRows page', () => {
    cy.visit('/');
    cy.clickOnEntityMenuItem('d-domakin-row');
    cy.wait('@entitiesRequest').then(({ response }) => {
      if (response?.body.length === 0) {
        cy.get(entityTableSelector).should('not.exist');
      } else {
        cy.get(entityTableSelector).should('exist');
      }
    });
    cy.getEntityHeading('DDomakinRow').should('exist');
    cy.url().should('match', dDomakinRowPageUrlPattern);
  });

  describe('DDomakinRow page', () => {
    describe('create button click', () => {
      beforeEach(() => {
        cy.visit(dDomakinRowPageUrl);
        cy.wait('@entitiesRequest');
      });

      it('should load create DDomakinRow page', () => {
        cy.get(entityCreateButtonSelector).click();
        cy.url().should('match', new RegExp('/d-domakin-row/new$'));
        cy.getEntityCreateUpdateHeading('DDomakinRow');
        cy.get(entityCreateSaveButtonSelector).should('exist');
        cy.get(entityCreateCancelButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response?.statusCode).to.equal(200);
        });
        cy.url().should('match', dDomakinRowPageUrlPattern);
      });
    });

    describe('with existing value', () => {
      beforeEach(() => {
        cy.authenticatedRequest({
          method: 'POST',
          url: '/api/d-domakin-rows',
          body: dDomakinRowSample,
        }).then(({ body }) => {
          dDomakinRow = body;

          cy.intercept(
            {
              method: 'GET',
              url: '/api/d-domakin-rows+(?*|)',
              times: 1,
            },
            {
              statusCode: 200,
              headers: {
                link: '<http://localhost/api/d-domakin-rows?page=0&size=20>; rel="last",<http://localhost/api/d-domakin-rows?page=0&size=20>; rel="first"',
              },
              body: [dDomakinRow],
            },
          ).as('entitiesRequestInternal');
        });

        cy.visit(dDomakinRowPageUrl);

        cy.wait('@entitiesRequestInternal');
      });

      it('detail button click should load details DDomakinRow page', () => {
        cy.get(entityDetailsButtonSelector).first().click();
        cy.getEntityDetailsHeading('dDomakinRow');
        cy.get(entityDetailsBackButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response?.statusCode).to.equal(200);
        });
        cy.url().should('match', dDomakinRowPageUrlPattern);
      });

      it('edit button click should load edit DDomakinRow page and go back', () => {
        cy.get(entityEditButtonSelector).first().click();
        cy.getEntityCreateUpdateHeading('DDomakinRow');
        cy.get(entityCreateSaveButtonSelector).should('exist');
        cy.get(entityCreateCancelButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response?.statusCode).to.equal(200);
        });
        cy.url().should('match', dDomakinRowPageUrlPattern);
      });

      it('edit button click should load edit DDomakinRow page and save', () => {
        cy.get(entityEditButtonSelector).first().click();
        cy.getEntityCreateUpdateHeading('DDomakinRow');
        cy.get(entityCreateSaveButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response?.statusCode).to.equal(200);
        });
        cy.url().should('match', dDomakinRowPageUrlPattern);
      });

      it('last delete button click should delete instance of DDomakinRow', () => {
        cy.intercept('GET', '/api/d-domakin-rows/*').as('dialogDeleteRequest');
        cy.get(entityDeleteButtonSelector).last().click();
        cy.wait('@dialogDeleteRequest');
        cy.getEntityDeleteDialogHeading('dDomakinRow').should('exist');
        cy.get(entityConfirmDeleteButtonSelector).click();
        cy.wait('@deleteEntityRequest').then(({ response }) => {
          expect(response?.statusCode).to.equal(204);
        });
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response?.statusCode).to.equal(200);
        });
        cy.url().should('match', dDomakinRowPageUrlPattern);

        dDomakinRow = undefined;
      });
    });
  });

  describe('new DDomakinRow page', () => {
    beforeEach(() => {
      cy.visit(`${dDomakinRowPageUrl}`);
      cy.get(entityCreateButtonSelector).click();
      cy.getEntityCreateUpdateHeading('DDomakinRow');
    });

    it('should create an instance of DDomakinRow', () => {
      cy.get(`[data-cy="dmVid"]`).type('lighthearted essential however');
      cy.get(`[data-cy="dmVid"]`).should('have.value', 'lighthearted essential however');

      cy.get(`[data-cy="nomPok"]`).type('warp');
      cy.get(`[data-cy="nomPok"]`).should('have.value', 'warp');

      cy.get(`[data-cy="domGraj"]`).type('but without');
      cy.get(`[data-cy="domGraj"]`).should('have.value', 'but without');

      cy.get(`[data-cy="domFamil"]`).type('once suspiciously');
      cy.get(`[data-cy="domFamil"]`).should('have.value', 'once suspiciously');

      cy.get(`[data-cy="domIme"]`).type('but save the');
      cy.get(`[data-cy="domIme"]`).should('have.value', 'but save the');

      cy.get(`[data-cy="domDarj"]`).type('pfft');
      cy.get(`[data-cy="domDarj"]`).should('have.value', 'pfft');

      cy.get(`[data-cy="domNm"]`).type('13376');
      cy.get(`[data-cy="domNm"]`).should('have.value', '13376');

      cy.get(`[data-cy="domAdres"]`).type('mobilize instructor that');
      cy.get(`[data-cy="domAdres"]`).should('have.value', 'mobilize instructor that');

      cy.get(`[data-cy="vedDarj"]`).type('concerning enormously');
      cy.get(`[data-cy="vedDarj"]`).should('have.value', 'concerning enormously');

      cy.get(`[data-cy="vedNm"]`).type('utterly');
      cy.get(`[data-cy="vedNm"]`).should('have.value', 'utterly');

      cy.get(entityCreateSaveButtonSelector).click();

      cy.wait('@postEntityRequest').then(({ response }) => {
        expect(response?.statusCode).to.equal(201);
        dDomakinRow = response.body;
      });
      cy.wait('@entitiesRequest').then(({ response }) => {
        expect(response?.statusCode).to.equal(200);
      });
      cy.url().should('match', dDomakinRowPageUrlPattern);
    });
  });
});
