package com.exscudo.peer.core.data.mapper.transport;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import com.exscudo.peer.core.data.Transaction;
import com.exscudo.peer.core.data.mapper.Constants;
import com.exscudo.peer.core.utils.Format;

/**
 * Convert Transaction to and from Map
 */
public class TransactionMapper {

	/**
	 * Convert transaction to map
	 *
	 * @param transaction
	 *            transaction
	 * @return Map
	 */
	public static Map<String, Object> convert(Transaction transaction) {
		Map<String, Object> map = new TreeMap<>();

		if (transaction.getSignature() != null) {
			map.put(Constants.SIGNATURE, Format.convert(transaction.getSignature()));
		}
		map.put(Constants.TYPE, transaction.getType());
		map.put(Constants.TIMESTAMP, transaction.getTimestamp());
		map.put(Constants.DEADLINE, transaction.getDeadline());
		map.put(Constants.FEE, transaction.getFee());
		map.put(Constants.VERSION, transaction.getVersion());
		if (transaction.getReference() != 0) {
			map.put(Constants.REFERENCED_TRANSACTION, Format.ID.transactionId(transaction.getReference()));
		}
		map.put(Constants.SENDER, Format.ID.accountId(transaction.getSenderID()));
		if (transaction.getData() != null) {
			map.put(Constants.ATTACHMENT, transaction.getData());
		} else {
			map.put(Constants.ATTACHMENT, new HashMap<>());
		}
		if (transaction.getConfirmations() != null && !transaction.getConfirmations().isEmpty()) {
			map.put(Constants.CONFIRMATIONS, transaction.getConfirmations());
		}

		return map;
	}

	/**
	 * Convert transaction from map
	 *
	 * @param map
	 *            map
	 * @return transaction
	 */
	public static Transaction convert(Map<String, Object> map) {

		Transaction tx = new Transaction();

		int type = Integer.parseInt(map.get(Constants.TYPE).toString());
		int timestamp = Integer.parseInt(map.get(Constants.TIMESTAMP).toString());
		int deadline = Integer.parseInt(map.get(Constants.DEADLINE).toString());
		long fee = Long.parseLong(map.get(Constants.FEE).toString());
		long referencedTransaction = 0L;
		if (map.containsKey(Constants.REFERENCED_TRANSACTION)) {
			referencedTransaction = Format.ID.transactionId(map.get(Constants.REFERENCED_TRANSACTION).toString());
		}
		long sender = Format.ID.accountId(map.get(Constants.SENDER).toString());
		byte[] signature = Format.convert(map.get(Constants.SIGNATURE).toString());

		int version = 1;
		if (map.containsKey(Constants.VERSION)) {
			version = Integer.parseInt(map.get(Constants.VERSION).toString());
		}

		Map<String, Object> attachment = null;
		Object obj = map.get(Constants.ATTACHMENT);
		if (obj != null && obj instanceof Map) {
			attachment = new HashMap<>();
			for (Object k : ((Map<?, ?>) obj).keySet()) {
				Object v = ((Map<?, ?>) obj).get(k);
				attachment.put(k.toString(), v);
			}
		}

		Map<String, Object> confirmations = null;
		Object cObj = map.get(Constants.CONFIRMATIONS);
		if (cObj != null && cObj instanceof Map) {
			confirmations = new HashMap<>();
			for (Object k : ((Map<?, ?>) cObj).keySet()) {
				Object v = ((Map<?, ?>) cObj).get(k);
				confirmations.put(k.toString(), v);
			}
		}

		tx.setType(type);
		tx.setVersion(version);
		tx.setTimestamp(timestamp);
		tx.setDeadline(deadline);
		tx.setReference(referencedTransaction);
		tx.setSenderID(sender);
		tx.setFee(fee);
		tx.setData(attachment);
		tx.setConfirmations(confirmations);
		tx.setSignature(signature);

		return tx;
	}

}
