package io.github.jam01.reales.domain;

import io.github.jam01.reales.domain.conversion.Transformation;
import io.github.jam01.reales.domain.exchange.Transfer;

/**
 * Association linking a continuant Economic Resource to an occurrent Economic Event in a relationship that maintains
 * data consistency, effected with either triggered or adjusting procedures. Stockflow associations may be decomposed
 * into {@link Transfer} for market exchanges and
 * {@link Transformation} for internal conversions.
 *
 * @see "Gal, Graham, Guido Geerts, and William E. McCarthy. 2022. 'The REA Accounting Model as an Accounting and
 * Economic Ontology.' American Accounting Association."
 */
public abstract class Stockflow {
    protected Resource resource;

    // TODO: 2/24/23 can we restrict visibility to only conversion and exchange subclasses
    protected Stockflow(Resource resource) {
        this.resource = resource;
    }

    public Resource resource() {
        return resource;
    }
}
