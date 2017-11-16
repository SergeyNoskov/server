package com.exscudo.peer.core.data;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

/**
 * Basic element of building a chain of blocks (blockchain).
 * <p>
 * Contains reference for next and previous block, contains transactions,
 * processed in block.
 */
public class Block extends SignedMessage implements Serializable {
	private static final long serialVersionUID = -8705643178030126672L;

	protected int version;
	protected long previousBlock;
	protected byte[] generationSignature;

	protected int height;
	protected long nextBlock;
	protected List<Transaction> transactions;
	protected BigInteger cumulativeDifficulty;
	protected byte[] snapshot;

	/**
	 * Returns the version of the block.
	 *
	 * @return
	 */
	public int getVersion() {
		return version;
	}

	/**
	 * Sets the version of the block.
	 *
	 * @param version
	 */
	public void setVersion(int version) {
		this.version = version;
	}

	/**
	 * Returns true if the creation time of the transaction is in the future.
	 *
	 * @param timestamp
	 *            Point in time for which the check is carried out.
	 * @return
	 */
	public boolean isFuture(int timestamp) {
		return getTimestamp() > timestamp;
	}

	/**
	 * Returns the ID of the previous block.
	 *
	 * @return
	 */
	public long getPreviousBlock() {
		return previousBlock;
	}

	/**
	 * Sets the ID of the previous block.
	 *
	 * @param previousBlock
	 */
	public void setPreviousBlock(long previousBlock) {
		this.previousBlock = previousBlock;
	}

	/**
	 * Returns the special field "signature generation" in order to prove possession
	 * address
	 *
	 * @return
	 */
	public byte[] getGenerationSignature() {
		return generationSignature;
	}

	/**
	 * Sets the special field "signature generation" in order to prove possession
	 * address
	 *
	 * @param generationSignature
	 */
	public void setGenerationSignature(byte[] generationSignature) {
		this.generationSignature = generationSignature;
	}

	/**
	 * Returns the block position in the chain.
	 *
	 * @return
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * Sets the position in the chain.
	 *
	 * @param height
	 */
	public void setHeight(int height) {
		this.height = height;
	}

	/**
	 * Sets transactions in block
	 *
	 * @param transactions
	 */
	public void setTransactions(Collection<Transaction> transactions) {
		Objects.requireNonNull(transactions);
		this.transactions = new ArrayList<>(transactions);
	}

	/**
	 * Returns transactions in block
	 *
	 * @return
	 */
	public Collection<Transaction> getTransactions() {
		return new ArrayList<>(this.transactions);
	}

	/**
	 * Returns the ID of the next block.
	 *
	 * @return
	 */
	public long getNextBlock() {
		return nextBlock;
	}

	/**
	 * Sets the ID of the next block.
	 *
	 * @param blockId
	 */
	public void setNextBlock(long blockId) {
		nextBlock = blockId;
	}

	/**
	 * Returns the Cumulative Difficulty of the current block.
	 *
	 * @return
	 */
	public BigInteger getCumulativeDifficulty() {
		if (cumulativeDifficulty == null) {
			throw new RuntimeException();
		}
		return cumulativeDifficulty;
	}

	/**
	 * Sets the Cumulative Difficulty of the current block.
	 *
	 * @param cumulativeDifficulty
	 */
	public void setCumulativeDifficulty(BigInteger cumulativeDifficulty) {
		this.cumulativeDifficulty = cumulativeDifficulty;
	}

	/**
	 * Returns the hash of the snapshot where the current state of accounts is
	 * saved.
	 *
	 * @return
	 */
	public byte[] getSnapshot() {
		return this.snapshot;
	}

	/**
	 * Returns the hash of the snapshot where the current state of accounts is
	 * saved.
	 *
	 * @param snapshot
	 */
	public void setSnapshot(byte[] snapshot) {
		this.snapshot = snapshot;
	}
}