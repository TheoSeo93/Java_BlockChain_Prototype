package core;

import java.util.List;

import util.Util;

/*
 * Block contains and processes the information.
 */
public class Block {
	private int blockID;
	private int nonce; // Nonce means the same as the "answer"
	private String data;
	private String prevBlockHash;
	private List<Transaction> txnList;

	public Block(int blockID, String prevBlockHash, int nonce, String data, List<Transaction> txnList) {
		this.blockID = blockID;
		this.prevBlockHash = prevBlockHash;
		this.nonce = nonce;
		this.data = data;
		this.txnList = txnList;
	}

	public Block(int blockID, String prevBlockHash, int nonce, String data) {
		this.blockID = blockID;
		this.prevBlockHash = prevBlockHash;
		this.nonce = nonce;
		this.data = data;
	}

	public Block(int blockID, int nonce, String data) {
		this.blockID = blockID;
		this.nonce = nonce;
		this.data = data;
	}

	public void addTxn(Transaction txn) {
		txnList.add(txn);
	}

	public String getPrevBlockHash() {
		return prevBlockHash;
	}

	public void setPrevBlockHash(String prevBlockHash) {
		this.prevBlockHash = prevBlockHash;
	}

	public int getBlockID() {
		return blockID;
	}

	public void setBlockID(int blockID) {
		this.blockID = blockID;
	}

	public int getNonce() {
		return nonce;
	}

	public void setNonce(int nonce) {
		this.nonce = nonce;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getBlockHash() {
		return Util.getHash(nonce + data + prevBlockHash);
	}

	public void mine() {
		while (true) {
			if (getBlockHash().substring(0, 4).equals("0000")) {
				System.out.println(blockID + " th block was successfully mined");
				break;
			}
			nonce++;
		}

	}

	public void getInformation() {
		System.out.println("Block ID: " + getBlockID());
		System.out.println("Value of Mined Variable(Nonce): " + getNonce());
		System.out.println("Block Data: " + getData());
		System.out.println("Block hash: " + getBlockHash());
		System.out.println("Prev hash: " + getPrevBlockHash());
		for(int i=0;i<txnList.size();i++)
			txnList.get(i).getInformation();
		System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
	}
}
