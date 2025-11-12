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

describe('DFingersRow e2e test', () => {
  const dFingersRowPageUrl = '/d-fingers-row';
  const dFingersRowPageUrlPattern = new RegExp('/d-fingers-row(\\?.*)?$');
  const username = Cypress.env('E2E_USERNAME') ?? 'user';
  const password = Cypress.env('E2E_PASSWORD') ?? 'user';
  const dFingersRowSample = {
    fnDatreg: '2025-10-29',
    fnDatvav: '2025-10-29T19:35:33.522Z',
    fnUsera: 'celebrate rally',
    fnVidmol: 'softly inasmuch concerning',
    fnDrugi: 'geez furthermore oxidize',
    fnFingerposition: 'waterlogged',
  };

  let dFingersRow;

  beforeEach(() => {
    cy.login(username, password);
  });

  beforeEach(() => {
    cy.intercept('GET', '/api/d-fingers-rows+(?*|)').as('entitiesRequest');
    cy.intercept('POST', '/api/d-fingers-rows').as('postEntityRequest');
    cy.intercept('DELETE', '/api/d-fingers-rows/*').as('deleteEntityRequest');
  });

  afterEach(() => {
    if (dFingersRow) {
      cy.authenticatedRequest({
        method: 'DELETE',
        url: `/api/d-fingers-rows/${dFingersRow.id}`,
      }).then(() => {
        dFingersRow = undefined;
      });
    }
  });

  it('DFingersRows menu should load DFingersRows page', () => {
    cy.visit('/');
    cy.clickOnEntityMenuItem('d-fingers-row');
    cy.wait('@entitiesRequest').then(({ response }) => {
      if (response?.body.length === 0) {
        cy.get(entityTableSelector).should('not.exist');
      } else {
        cy.get(entityTableSelector).should('exist');
      }
    });
    cy.getEntityHeading('DFingersRow').should('exist');
    cy.url().should('match', dFingersRowPageUrlPattern);
  });

  describe('DFingersRow page', () => {
    describe('create button click', () => {
      beforeEach(() => {
        cy.visit(dFingersRowPageUrl);
        cy.wait('@entitiesRequest');
      });

      it('should load create DFingersRow page', () => {
        cy.get(entityCreateButtonSelector).click();
        cy.url().should('match', new RegExp('/d-fingers-row/new$'));
        cy.getEntityCreateUpdateHeading('DFingersRow');
        cy.get(entityCreateSaveButtonSelector).should('exist');
        cy.get(entityCreateCancelButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response?.statusCode).to.equal(200);
        });
        cy.url().should('match', dFingersRowPageUrlPattern);
      });
    });

    describe('with existing value', () => {
      beforeEach(() => {
        cy.authenticatedRequest({
          method: 'POST',
          url: '/api/d-fingers-rows',
          body: dFingersRowSample,
        }).then(({ body }) => {
          dFingersRow = body;

          cy.intercept(
            {
              method: 'GET',
              url: '/api/d-fingers-rows+(?*|)',
              times: 1,
            },
            {
              statusCode: 200,
              headers: {
                link: '<http://localhost/api/d-fingers-rows?page=0&size=20>; rel="last",<http://localhost/api/d-fingers-rows?page=0&size=20>; rel="first"',
              },
              body: [dFingersRow],
            },
          ).as('entitiesRequestInternal');
        });

        cy.visit(dFingersRowPageUrl);

        cy.wait('@entitiesRequestInternal');
      });

      it('detail button click should load details DFingersRow page', () => {
        cy.get(entityDetailsButtonSelector).first().click();
        cy.getEntityDetailsHeading('dFingersRow');
        cy.get(entityDetailsBackButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response?.statusCode).to.equal(200);
        });
        cy.url().should('match', dFingersRowPageUrlPattern);
      });

      it('edit button click should load edit DFingersRow page and go back', () => {
        cy.get(entityEditButtonSelector).first().click();
        cy.getEntityCreateUpdateHeading('DFingersRow');
        cy.get(entityCreateSaveButtonSelector).should('exist');
        cy.get(entityCreateCancelButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response?.statusCode).to.equal(200);
        });
        cy.url().should('match', dFingersRowPageUrlPattern);
      });

      it('edit button click should load edit DFingersRow page and save', () => {
        cy.get(entityEditButtonSelector).first().click();
        cy.getEntityCreateUpdateHeading('DFingersRow');
        cy.get(entityCreateSaveButtonSelector).click();
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response?.statusCode).to.equal(200);
        });
        cy.url().should('match', dFingersRowPageUrlPattern);
      });

      it('last delete button click should delete instance of DFingersRow', () => {
        cy.intercept('GET', '/api/d-fingers-rows/*').as('dialogDeleteRequest');
        cy.get(entityDeleteButtonSelector).last().click();
        cy.wait('@dialogDeleteRequest');
        cy.getEntityDeleteDialogHeading('dFingersRow').should('exist');
        cy.get(entityConfirmDeleteButtonSelector).click();
        cy.wait('@deleteEntityRequest').then(({ response }) => {
          expect(response?.statusCode).to.equal(204);
        });
        cy.wait('@entitiesRequest').then(({ response }) => {
          expect(response?.statusCode).to.equal(200);
        });
        cy.url().should('match', dFingersRowPageUrlPattern);

        dFingersRow = undefined;
      });
    });
  });

  describe('new DFingersRow page', () => {
    beforeEach(() => {
      cy.visit(`${dFingersRowPageUrl}`);
      cy.get(entityCreateButtonSelector).click();
      cy.getEntityCreateUpdateHeading('DFingersRow');
    });

    it('should create an instance of DFingersRow', () => {
      cy.get(`[data-cy="fnDatreg"]`).type('2025-10-29');
      cy.get(`[data-cy="fnDatreg"]`).blur();
      cy.get(`[data-cy="fnDatreg"]`).should('have.value', '2025-10-29');

      cy.get(`[data-cy="fnDatvav"]`).type('2025-10-30T08:05');
      cy.get(`[data-cy="fnDatvav"]`).blur();
      cy.get(`[data-cy="fnDatvav"]`).should('have.value', '2025-10-30T08:05');

      cy.get(`[data-cy="fnUsera"]`).type('annually parade geez');
      cy.get(`[data-cy="fnUsera"]`).should('have.value', 'annually parade geez');

      cy.get(`[data-cy="fnSid"]`).type('8368');
      cy.get(`[data-cy="fnSid"]`).should('have.value', '8368');

      cy.get(`[data-cy="fnPnr"]`).type('25297');
      cy.get(`[data-cy="fnPnr"]`).should('have.value', '25297');

      cy.get(`[data-cy="fnVidmol"]`).type('knottily sushi wherever');
      cy.get(`[data-cy="fnVidmol"]`).should('have.value', 'knottily sushi wherever');

      cy.get(`[data-cy="fnDrugi"]`).type('provision unaccountably');
      cy.get(`[data-cy="fnDrugi"]`).should('have.value', 'provision unaccountably');

      cy.get(`[data-cy="fnDeviceid"]`).type('31467');
      cy.get(`[data-cy="fnDeviceid"]`).should('have.value', '31467');

      cy.get(`[data-cy="fnScanres"]`).type('3833');
      cy.get(`[data-cy="fnScanres"]`).should('have.value', '3833');

      cy.get(`[data-cy="fnWidth"]`).type('26283');
      cy.get(`[data-cy="fnWidth"]`).should('have.value', '26283');

      cy.get(`[data-cy="fnHeight"]`).type('24597');
      cy.get(`[data-cy="fnHeight"]`).should('have.value', '24597');

      cy.get(`[data-cy="fnPixeldepth"]`).type('25789');
      cy.get(`[data-cy="fnPixeldepth"]`).should('have.value', '25789');

      cy.get(`[data-cy="fnCompressalgo"]`).type('22021');
      cy.get(`[data-cy="fnCompressalgo"]`).should('have.value', '22021');

      cy.get(`[data-cy="fnFingerposition"]`).type('meh although pause');
      cy.get(`[data-cy="fnFingerposition"]`).should('have.value', 'meh although pause');

      cy.get(`[data-cy="fnImagequality"]`).type('23813');
      cy.get(`[data-cy="fnImagequality"]`).should('have.value', '23813');

      cy.get(`[data-cy="fnImage"]`).type('confound valiantly canter');
      cy.get(`[data-cy="fnImage"]`).should('have.value', 'confound valiantly canter');

      cy.get(`[data-cy="fnImglen"]`).type('484');
      cy.get(`[data-cy="fnImglen"]`).should('have.value', '484');

      cy.get(`[data-cy="fnNottakenreason"]`).type('yowza');
      cy.get(`[data-cy="fnNottakenreason"]`).should('have.value', 'yowza');

      cy.get(entityCreateSaveButtonSelector).click();

      cy.wait('@postEntityRequest').then(({ response }) => {
        expect(response?.statusCode).to.equal(201);
        dFingersRow = response.body;
      });
      cy.wait('@entitiesRequest').then(({ response }) => {
        expect(response?.statusCode).to.equal(200);
      });
      cy.url().should('match', dFingersRowPageUrlPattern);
    });
  });
});
