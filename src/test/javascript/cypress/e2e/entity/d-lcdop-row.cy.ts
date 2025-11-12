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

describe('DLcdopRow e2e test', () => {
  const dLcdopRowPageUrl = '/d-lcdop-row';
  const dLcdopRowPageUrlPattern = new RegExp('/d-lcdop-row(\\?.*)?$');
  const username = Cypress.env('E2E_USERNAME') ?? 'user';
  const password = Cypress.env('E2E_PASSWORD') ?? 'user';
  const dLcdopRowSample = {
    ldMrjdarj: 'skeleton',
    ldMrjnm: 'without indeed',
    ldMrjgraj: 'deeply',
    ldZenen: 'phrase',
    ldJitDarj: 'or so',
    ldJitNm: 'information',
    ldJitUl: 'concrete',
    ldRabota: 'along till',
    ldProfkod: 'daily',
    ldProfesia: 'expostulate instead finally',
    ldSlDarj: 'rule after amount',
    ldSlNm: 'within pro lest',
    ldSlUl: 'eek',
  };

  let dLcdopRow;

  beforeEach(() => {
    cy.login(username, password);
  });

  beforeEach(() => {
    cy.intercept('GET', '/api/d-lcdop-rows+(?*|)').as('entitiesRequest');
    cy.intercept('POST', '/api/d-lcdop-rows').as('postEntityRequest');
    cy.intercept('DELETE', '/api/d-lcdop-rows/*').as('deleteEntityRequest');
  });

  afterEach(() => {
    if (dLcdopRow) {
      cy.authenticatedRequest({
        method: 'DELETE',
        url: `/api/d-lcdop-rows/${dLcdopRow.id}`,
      }).then(() => {
        dLcdopRow = undefined;
      });
    }
  });

  it('DLcdopRows menu should load DLcdopRows page', () => {
    cy.visit('/');
    cy.clickOnEntityMenuItem('d-lcdop-row');
    cy.wait('@entitiesRequest').then(({ response }) => {
      if (response?.body.length === 0) {
        cy.get(entityTableSelector).should('not.exist');
      } else {
        cy.get(entityTableSelector).should('exist');
      }
    });
    cy.getEntityHeading('DLcdopRow').should('exist');
    cy.url().should('match', dLcdopRowPageUrlPattern);
  });

  describe('DLcdopRow page', () => {
    describe('create button click', () => {
      beforeEach(() => {
        cy.visit(dLcdopRowPageUrl);
        cy.wait('@entitiesRequest');
      });

      it('should load create DLcdopRow page', () => {
        cy.get(entityCreateButtonSelector).click();
        cy.url().should('match', new RegExp('/d-lcdop-row/new$'));
        cy.getEntityCreateUpdateHeading('DLcdopRow');
        cy.get(entityCreateSaveButtonSelector).should('exist');
        cy.get(entityCreateCancelButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response?.statusCode).to.equal(200);
        });
        cy.url().should('match', dLcdopRowPageUrlPattern);
      });
    });

    describe('with existing value', () => {
      beforeEach(() => {
        cy.authenticatedRequest({
          method: 'POST',
          url: '/api/d-lcdop-rows',
          body: dLcdopRowSample,
        }).then(({ body }) => {
          dLcdopRow = body;

          cy.intercept(
            {
              method: 'GET',
              url: '/api/d-lcdop-rows+(?*|)',
              times: 1,
            },
            {
              statusCode: 200,
              headers: {
                link: '<http://localhost/api/d-lcdop-rows?page=0&size=20>; rel="last",<http://localhost/api/d-lcdop-rows?page=0&size=20>; rel="first"',
              },
              body: [dLcdopRow],
            },
          ).as('entitiesRequestInternal');
        });

        cy.visit(dLcdopRowPageUrl);

        cy.wait('@entitiesRequestInternal');
      });

      it('detail button click should load details DLcdopRow page', () => {
        cy.get(entityDetailsButtonSelector).first().click();
        cy.getEntityDetailsHeading('dLcdopRow');
        cy.get(entityDetailsBackButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response?.statusCode).to.equal(200);
        });
        cy.url().should('match', dLcdopRowPageUrlPattern);
      });

      it('edit button click should load edit DLcdopRow page and go back', () => {
        cy.get(entityEditButtonSelector).first().click();
        cy.getEntityCreateUpdateHeading('DLcdopRow');
        cy.get(entityCreateSaveButtonSelector).should('exist');
        cy.get(entityCreateCancelButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response?.statusCode).to.equal(200);
        });
        cy.url().should('match', dLcdopRowPageUrlPattern);
      });

      it('edit button click should load edit DLcdopRow page and save', () => {
        cy.get(entityEditButtonSelector).first().click();
        cy.getEntityCreateUpdateHeading('DLcdopRow');
        cy.get(entityCreateSaveButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response?.statusCode).to.equal(200);
        });
        cy.url().should('match', dLcdopRowPageUrlPattern);
      });

      it('last delete button click should delete instance of DLcdopRow', () => {
        cy.intercept('GET', '/api/d-lcdop-rows/*').as('dialogDeleteRequest');
        cy.get(entityDeleteButtonSelector).last().click();
        cy.wait('@dialogDeleteRequest');
        cy.getEntityDeleteDialogHeading('dLcdopRow').should('exist');
        cy.get(entityConfirmDeleteButtonSelector).click();
        cy.wait('@deleteEntityRequest').then(({ response }) => {
          expect(response?.statusCode).to.equal(204);
        });
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response?.statusCode).to.equal(200);
        });
        cy.url().should('match', dLcdopRowPageUrlPattern);

        dLcdopRow = undefined;
      });
    });
  });

  describe('new DLcdopRow page', () => {
    beforeEach(() => {
      cy.visit(`${dLcdopRowPageUrl}`);
      cy.get(entityCreateButtonSelector).click();
      cy.getEntityCreateUpdateHeading('DLcdopRow');
    });

    it('should create an instance of DLcdopRow', () => {
      cy.get(`[data-cy="ldMrjdarj"]`).type('loftily lovingly');
      cy.get(`[data-cy="ldMrjdarj"]`).should('have.value', 'loftily lovingly');

      cy.get(`[data-cy="ldMrjnm"]`).type('regarding restfully pace');
      cy.get(`[data-cy="ldMrjnm"]`).should('have.value', 'regarding restfully pace');

      cy.get(`[data-cy="ldMrjgraj"]`).type('where');
      cy.get(`[data-cy="ldMrjgraj"]`).should('have.value', 'where');

      cy.get(`[data-cy="ldZenen"]`).type('under ack aged');
      cy.get(`[data-cy="ldZenen"]`).should('have.value', 'under ack aged');

      cy.get(`[data-cy="ldJitDarj"]`).type('frank verbally');
      cy.get(`[data-cy="ldJitDarj"]`).should('have.value', 'frank verbally');

      cy.get(`[data-cy="ldJitNm"]`).type('unsteady object');
      cy.get(`[data-cy="ldJitNm"]`).should('have.value', 'unsteady object');

      cy.get(`[data-cy="ldJitUl"]`).type('quantify if');
      cy.get(`[data-cy="ldJitUl"]`).should('have.value', 'quantify if');

      cy.get(`[data-cy="ldTel"]`).type('11319');
      cy.get(`[data-cy="ldTel"]`).should('have.value', '11319');

      cy.get(`[data-cy="ldRabota"]`).type('eek');
      cy.get(`[data-cy="ldRabota"]`).should('have.value', 'eek');

      cy.get(`[data-cy="ldProfkod"]`).type('since anenst');
      cy.get(`[data-cy="ldProfkod"]`).should('have.value', 'since anenst');

      cy.get(`[data-cy="ldProfesia"]`).type('gosh');
      cy.get(`[data-cy="ldProfesia"]`).should('have.value', 'gosh');

      cy.get(`[data-cy="ldSlDarj"]`).type('glossy');
      cy.get(`[data-cy="ldSlDarj"]`).should('have.value', 'glossy');

      cy.get(`[data-cy="ldSlNm"]`).type('mindless');
      cy.get(`[data-cy="ldSlNm"]`).should('have.value', 'mindless');

      cy.get(`[data-cy="ldSlUl"]`).type('until athwart');
      cy.get(`[data-cy="ldSlUl"]`).should('have.value', 'until athwart');

      cy.get(entityCreateSaveButtonSelector).click();

      cy.wait('@postEntityRequest').then(({ response }) => {
        expect(response?.statusCode).to.equal(201);
        dLcdopRow = response.body;
      });
      cy.wait('@entitiesRequest').then(({ response }) => {
        expect(response?.statusCode).to.equal(200);
      });
      cy.url().should('match', dLcdopRowPageUrlPattern);
    });
  });
});
