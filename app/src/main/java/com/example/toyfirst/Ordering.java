package com.example.toyfirst;

public class Ordering {
    private int OID;
    private String NICNO;
    private String FullName;
    private String PAddress;
    private String ContactNO;

    public int getOID() {
        return OID;
    }

    public void setOID(int OID) {
        this.OID = OID;
    }

    public String getNICNO() {
        return NICNO;
    }

    public void setNICNO(String NICNO) {
        this.NICNO = NICNO;
    }

    public String getFullName() {
        return FullName;
    }

    public void setFullName(String fullName) {
        FullName = fullName;
    }

    public String getPAddress() {
        return PAddress;
    }

    public void setPAddress(String PAddress) {
        this.PAddress = PAddress;
    }

    public String getContactNO() {
        return ContactNO;
    }

    public void setContactNO(String contactNO) {
        ContactNO = contactNO;
    }
}
