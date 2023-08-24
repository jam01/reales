package io.github.jam01.reales.domain;

import io.github.jam01.reales.domain.attributes.Attribute;
import org.jspecify.annotations.Nullable;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * The agreement between two Economic Agents to a bundling of reciprocated Economic Commitments, each of which details
 * the specific or abstract nature of the resources to be exchanged or converted.
 *
 * @see "Gal, Graham, Guido Geerts, and William E. McCarthy. 2022. 'The REA Accounting Model as an Accounting and
 * Economic Ontology.' American Accounting Association."
 */
public class Contract extends Agreement {
    // TODO: 3/19/23 withCommitments
    // TODO: 3/19/23 extendCommitments
    // TODO: 3/19/23 complete
    // TODO: 3/19/23 isComplete
    // TODO: 3/19/23 test methods for withCommitments, extendCommitments, complete

    public final @Nullable ContractType type;
    public List<? extends Commitment> commitments; // TODO: 3/19/23 should these be bounded?
    private final Map<String, Attribute<?>> attributes;
    private final Function<Contract, Void> invariants;
    private final BiFunction<Contract, List<Commitment>, OperationResult<Contract>> updateCommitmentsFunc;

    public Contract(List<? extends Commitment> commitments) {
        this(null, commitments, null, null, null);
    }

    public Contract(@Nullable ContractType type, List<? extends Commitment> commitments,
                    Map<String, Attribute<?>> attributes,
                    Function<Contract, Void> invariants,
                    BiFunction<Contract, List<Commitment>, OperationResult<Contract>> updateCommitmentsFunc) {
        this.type = type;
        this.commitments = commitments != null ? Collections.unmodifiableList(commitments) : Collections.emptyList();

        this.attributes = attributes != null ? Collections.unmodifiableMap(attributes) : Collections.emptyMap();
        this.invariants = invariants != null ? invariants : contract -> null;
        this.updateCommitmentsFunc = updateCommitmentsFunc != null ? updateCommitmentsFunc : (contract, commitments1) -> null;

        this.invariants.apply(this);
    }

    /**
     * Whether this Contract has been completed. Predicated on entire commitment bundle being fulfilled.
     *
     * @return true if all Commitments have been fulfilled, false otherwise.
     */
    public boolean isComplete() {
        for (Commitment commitment : commitments) {
            if (!commitment.isFulfilled) return false;
        }

        return true;
    }

    /**
     * The type of Contract
     *
     * @return Optional[ContractType] of this Contract
     */
    public Optional<ContractType> type() {
        return Optional.ofNullable(type);
    }

    public List<? extends Commitment> commitments() {
        return commitments;
    }

    /**
     * The parties engaged in this Contract
     *
     * @return Set[Agent] collected from this Contract's Commitments
     */
    public Set<Agent> parties() {
        // TODO: 2/25/23 memoize
        var toReturn = new HashSet<Agent>();
        for (Commitment commitment : commitments) {
            toReturn.add(commitment.provider);
            toReturn.add(commitment.receiver);
        }

        return toReturn;
    }

    /**
     * Update this Contract's Commitments with updated instances.
     *
     * @param commitments1 The updated Commitments to associate with this Contract
     * @return An updated Contract instance
     */
    public OperationResult<Contract> updateCommitments(List<Commitment> commitments1) {
        Objects.requireNonNull(commitments1);

        return updateCommitmentsFunc.apply(this, commitments1);
    }

    public Optional<Attribute<?>> attribute(String name) {
        return Optional.ofNullable(attributes.get(name));
    }

    public Collection<Attribute<?>> attributes() {
        return attributes.values();
    }
}
