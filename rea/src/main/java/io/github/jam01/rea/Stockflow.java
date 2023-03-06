package io.github.jam01.rea;

/**
 * Association linking a continuant Economic Resource to an occurrent Economic Event in a relationship that maintains
 * data consistency, effected with either triggered or adjusting procedures. Stockflow associations may be decomposed
 * into {@link io.github.jam01.rea.exchange.Transfer} for market exchanges and
 * {@link io.github.jam01.rea.conversion.Transformation} for internal conversions.
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
