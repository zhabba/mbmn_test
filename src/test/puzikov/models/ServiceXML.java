package test.puzikov.models;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "ServiceXML")
@XmlAccessorType(XmlAccessType.FIELD)
public class ServiceXML {
    @XmlElement(name = "Transaction")
    private Transaction transaction = new Transaction();
    @XmlElement(name = "AccountInfo")
    private AccountInfo accountInfo = new AccountInfo();

    public Transaction getTransaction() {
        return transaction;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }

    public AccountInfo getAccountInfo() {
        return accountInfo;
    }

    public void setAccountInfo(AccountInfo accountInfo) {
        this.accountInfo = accountInfo;
    }
}