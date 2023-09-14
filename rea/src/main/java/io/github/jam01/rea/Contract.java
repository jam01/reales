package io.github.jam01.rea;

import org.jspecify.annotations.Nullable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

/**
 * The agreement between two Economic Agents to a bundling of reciprocated Economic Commitments, each of which details
 * the specific or abstract nature of the resources to be exchanged or converted.
 *
 * @see "Gal, Graham, Guido Geerts, and William E. McCarthy. 2022. 'The REA Accounting Model as an Accounting and
 * Economic Ontology.' American Accounting Association."
 */
public abstract class Contract extends Agreement {
    protected @Nullable ContractType type;
    protected List<? extends Commitment> commitments;

    protected Contract(List<? extends Commitment> commitments) {
        this(null, commitments);
    }

    protected Contract(@Nullable ContractType type, List<? extends Commitment> commitments) {
        this.type = type;
        this.commitments = commitments != null ? Collections.unmodifiableList(commitments) : Collections.emptyList();
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

    // TODO: 2/25/23 memoize

    /**
     * The parties engaged in this Contract
     *
     * @return Set[Agent] collected from this Contract's Commitments
     */
    public Set<? extends Agent> parties() {
        var toReturn = new HashSet<Agent>();
        for (Commitment commitment : commitments) {
            toReturn.add(commitment.provider);
            toReturn.add(commitment.receiver);
        }

        return toReturn;
    }

    /**
     * Set the given Commitments for this Contract.
     *
     * @param commitments1 The Commitments to associate with this Contract
     * @return An updated Contract instance
     */
    protected abstract Result<? extends Contract> withCommitments(List<? extends Commitment> commitments1);

    /**
     * Update this Contract's Commitments with updated instances.
     *
     * @param commitments1 The updated Commitments to associate with this Contract
     * @return An updated Contract instance
     */
    public Result<? extends Contract> updateCommitments(List<? extends Commitment> commitments1) {
        Objects.requireNonNull(commitments1);

        return withCommitments(commitments1);
    }

    /**
     * Extend this Contract's Commitments
     *
     * @param commitments1 The additional Commitments
     * @return An updated Contract instance
     */
    protected Result<? extends Contract> extendCommitments(List<? extends Commitment> commitments1) {
        Objects.requireNonNull(commitments1);

        List<Commitment> list = new ArrayList<>(commitments.size() + commitments1.size());
        list.addAll(commitments);
        list.addAll(commitments1);

        return this.withCommitments(list);
    }
}
