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

describe('DLcuzRow e2e test', () => {
  const dLcuzRowPageUrl = '/d-lcuz-row';
  const dLcuzRowPageUrlPattern = new RegExp('/d-lcuz-row(\\?.*)?$');
  const username = Cypress.env('E2E_USERNAME') ?? 'user';
  const password = Cypress.env('E2E_PASSWORD') ?? 'user';
  const dLcuzRowSample = {
    vidZp: 'excluding',
    nacBel: 'shipper anenst any',
    paspVal: '2025-10-30',
    graj: 'minus',
    famil: 'unto broadly',
    imena: 'poorly',
    datRaj: 'beside during',
    pol: 'until marksman brr',
    datIzd: '2025-10-29',
  };

  let dLcuzRow;

  beforeEach(() => {
    cy.login(username, password);
  });

  beforeEach(() => {
    cy.intercept('GET', '/api/d-lcuz-rows+(?*|)').as('entitiesRequest');
    cy.intercept('POST', '/api/d-lcuz-rows').as('postEntityRequest');
    cy.intercept('DELETE', '/api/d-lcuz-rows/*').as('deleteEntityRequest');
  });

  afterEach(() => {
    if (dLcuzRow) {
      cy.authenticatedRequest({
        method: 'DELETE',
        url: `/api/d-lcuz-rows/${dLcuzRow.id}`,
      }).then(() => {
        dLcuzRow = undefined;
      });
    }
  });

  it('DLcuzRows menu should load DLcuzRows page', () => {
    cy.visit('/');
    cy.clickOnEntityMenuItem('d-lcuz-row');
    cy.wait('@entitiesRequest').then(({ response }) => {
      if (response?.body.length === 0) {
        cy.get(entityTableSelector).should('not.exist');
      } else {
        cy.get(entityTableSelector).should('exist');
      }
    });
    cy.getEntityHeading('DLcuzRow').should('exist');
    cy.url().should('match', dLcuzRowPageUrlPattern);
  });

  describe('DLcuzRow page', () => {
    describe('create button click', () => {
      beforeEach(() => {
        cy.visit(dLcuzRowPageUrl);
        cy.wait('@entitiesRequest');
      });

      it('should load create DLcuzRow page', () => {
        cy.get(entityCreateButtonSelector).click();
        cy.url().should('match', new RegExp('/d-lcuz-row/new$'));
        cy.getEntityCreateUpdateHeading('DLcuzRow');
        cy.get(entityCreateSaveButtonSelector).should('exist');
        cy.get(entityCreateCancelButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response?.statusCode).to.equal(200);
        });
        cy.url().should('match', dLcuzRowPageUrlPattern);
      });
    });

    describe('with existing value', () => {
      beforeEach(() => {
        cy.authenticatedRequest({
          method: 'POST',
          url: '/api/d-lcuz-rows',
          body: dLcuzRowSample,
        }).then(({ body }) => {
          dLcuzRow = body;

          cy.intercept(
            {
              method: 'GET',
              url: '/api/d-lcuz-rows+(?*|)',
              times: 1,
            },
            {
              statusCode: 200,
              headers: {
                link: '<http://localhost/api/d-lcuz-rows?page=0&size=20>; rel="last",<http://localhost/api/d-lcuz-rows?page=0&size=20>; rel="first"',
              },
              body: [dLcuzRow],
            },
          ).as('entitiesRequestInternal');
        });

        cy.visit(dLcuzRowPageUrl);

        cy.wait('@entitiesRequestInternal');
      });

      it('detail button click should load details DLcuzRow page', () => {
        cy.get(entityDetailsButtonSelector).first().click();
        cy.getEntityDetailsHeading('dLcuzRow');
        cy.get(entityDetailsBackButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response?.statusCode).to.equal(200);
        });
        cy.url().should('match', dLcuzRowPageUrlPattern);
      });

      it('edit button click should load edit DLcuzRow page and go back', () => {
        cy.get(entityEditButtonSelector).first().click();
        cy.getEntityCreateUpdateHeading('DLcuzRow');
        cy.get(entityCreateSaveButtonSelector).should('exist');
        cy.get(entityCreateCancelButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response?.statusCode).to.equal(200);
        });
        cy.url().should('match', dLcuzRowPageUrlPattern);
      });

      it('edit button click should load edit DLcuzRow page and save', () => {
        cy.get(entityEditButtonSelector).first().click();
        cy.getEntityCreateUpdateHeading('DLcuzRow');
        cy.get(entityCreateSaveButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response?.statusCode).to.equal(200);
        });
        cy.url().should('match', dLcuzRowPageUrlPattern);
      });

      it('last delete button click should delete instance of DLcuzRow', () => {
        cy.intercept('GET', '/api/d-lcuz-rows/*').as('dialogDeleteRequest');
        cy.get(entityDeleteButtonSelector).last().click();
        cy.wait('@dialogDeleteRequest');
        cy.getEntityDeleteDialogHeading('dLcuzRow').should('exist');
        cy.get(entityConfirmDeleteButtonSelector).click();
        cy.wait('@deleteEntityRequest').then(({ response }) => {
          expect(response?.statusCode).to.equal(204);
        });
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response?.statusCode).to.equal(200);
        });
        cy.url().should('match', dLcuzRowPageUrlPattern);

        dLcuzRow = undefined;
      });
    });
  });

  describe('new DLcuzRow page', () => {
    beforeEach(() => {
      cy.visit(`${dLcuzRowPageUrl}`);
      cy.get(entityCreateButtonSelector).click();
      cy.getEntityCreateUpdateHeading('DLcuzRow');
    });

    it('should create an instance of DLcuzRow', () => {
      cy.get(`[data-cy="vidZp"]`).type('mysterious between ouch');
      cy.get(`[data-cy="vidZp"]`).should('have.value', 'mysterious between ouch');

      cy.get(`[data-cy="nacBel"]`).type('gracefully');
      cy.get(`[data-cy="nacBel"]`).should('have.value', 'gracefully');

      cy.get(`[data-cy="nacPasp"]`).type('12808');
      cy.get(`[data-cy="nacPasp"]`).should('have.value', '12808');

      cy.get(`[data-cy="paspVal"]`).type('2025-10-29');
      cy.get(`[data-cy="paspVal"]`).blur();
      cy.get(`[data-cy="paspVal"]`).should('have.value', '2025-10-29');

      cy.get(`[data-cy="graj"]`).type('boohoo');
      cy.get(`[data-cy="graj"]`).should('have.value', 'boohoo');

      cy.get(`[data-cy="famil"]`).type('catalyst though');
      cy.get(`[data-cy="famil"]`).should('have.value', 'catalyst though');

      cy.get(`[data-cy="imena"]`).type('yippee for');
      cy.get(`[data-cy="imena"]`).should('have.value', 'yippee for');

      cy.get(`[data-cy="datRaj"]`).type('ack amongst mob');
      cy.get(`[data-cy="datRaj"]`).should('have.value', 'ack amongst mob');

      cy.get(`[data-cy="pol"]`).type('devil limply oof');
      cy.get(`[data-cy="pol"]`).should('have.value', 'devil limply oof');

      cy.get(`[data-cy="datIzd"]`).type('2025-10-29');
      cy.get(`[data-cy="datIzd"]`).blur();
      cy.get(`[data-cy="datIzd"]`).should('have.value', '2025-10-29');

      cy.get(entityCreateSaveButtonSelector).click();

      cy.wait('@postEntityRequest').then(({ response }) => {
        expect(response?.statusCode).to.equal(201);
        dLcuzRow = response.body;
      });
      cy.wait('@entitiesRequest').then(({ response }) => {
        expect(response?.statusCode).to.equal(200);
      });
      cy.url().should('match', dLcuzRowPageUrlPattern);
    });
  });
});
