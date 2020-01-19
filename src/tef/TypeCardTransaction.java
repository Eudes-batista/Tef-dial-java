package tef;

public enum TypeCardTransaction {
    CREDIT_SIGHT("CREDITO AVISTA", 10), CREDIT_INSTALLMENTE("CREDITO PARCELADO ESTB", 11),
    CREDIT_INSTALLMENTE_ADMINISTRATOR("CREDITO PARCELADO ADM", 12),
    PRE_AUTHORIZATION_CREDIT("PRE AUTORIZACAO CREDITO", 13),
    DEBIT_SIGHT("DEBITO AVISTA", 20), PRE_DATE_DEBT("DEBITO PRE-DATADO", 21),
    DEBIT_INSTALLMENTE("DEBITO PARCELADO", 22), DEBIT_SIGHT_FORCE("DEBITO AVISTA FORCADO", 23),
    DEBIT_PRE_DATE_FORCE("DEBITO PRE DATADO FORCADO", 24),
    DEBIT_PRE_DATE_NO_WARRANTY("DEBITO PRE DATADO S.GARANTIA", 25),
    OTHER_CARDS("OUTROS CARTOES", 30), CDC("CDC", 40), QUERY_CDC("CONSULTA CDC", 41),
    HEALTH_INSURANCE("CONVENIO", 50), VOUCHER("DEBITO VOUCHER", 60), OTHER("OUTRAS", 99);

    private final String name;
    private final int typeTransaction;

    private TypeCardTransaction(String name, int typeTransaction) {
        this.name = name;
        this.typeTransaction = typeTransaction;
    }

    public String getName() {
        return name;
    }

    public int getTypeTransaction() {
        return typeTransaction;
    }
}
