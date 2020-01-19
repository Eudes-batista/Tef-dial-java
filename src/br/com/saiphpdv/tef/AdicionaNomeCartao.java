package br.com.saiphpdv.tef;

public class AdicionaNomeCartao {
    
    private static AdicionaNomeCartao adicionaNomeCartao = null;
    private static String nameCardTransaction;
    private static String network;

    public static AdicionaNomeCartao getAdicionaNomeCartao(String _nameCardTransaction,String _network) {
        if(adicionaNomeCartao == null){
            adicionaNomeCartao = new AdicionaNomeCartao();
        }
        nameCardTransaction = _nameCardTransaction;
        network = _network;
        return adicionaNomeCartao;
    }

    public String addNameCard(Integer transactionType) {
        if(TypeCardTransaction.CDC.getTypeTransaction() == transactionType){
            nameCardTransaction = network+" "+TypeCardTransaction.CDC.getName();
            return nameCardTransaction;
        }
        if(TypeCardTransaction.CREDIT_INSTALLMENTE.getTypeTransaction() == transactionType){
            nameCardTransaction = network+" "+TypeCardTransaction.CREDIT_INSTALLMENTE.getName();
            return nameCardTransaction;
        }
        if(TypeCardTransaction.CREDIT_INSTALLMENTE_ADMINISTRATOR.getTypeTransaction() == transactionType){
            nameCardTransaction = network+" "+TypeCardTransaction.CREDIT_INSTALLMENTE_ADMINISTRATOR.getName();
            return nameCardTransaction;
        }
        if(TypeCardTransaction.CREDIT_SIGHT.getTypeTransaction() == transactionType){
            nameCardTransaction = network+" "+TypeCardTransaction.CREDIT_SIGHT.getName();
            return nameCardTransaction;
        }
        if(TypeCardTransaction.DEBIT_INSTALLMENTE.getTypeTransaction() == transactionType){
            nameCardTransaction = network+" "+TypeCardTransaction.DEBIT_INSTALLMENTE.getName();
            return nameCardTransaction;
        }
        if(TypeCardTransaction.DEBIT_PRE_DATE_FORCE.getTypeTransaction() == transactionType){
            nameCardTransaction = network+" "+TypeCardTransaction.DEBIT_PRE_DATE_FORCE.getName();
            return nameCardTransaction;
        }
        if(TypeCardTransaction.DEBIT_PRE_DATE_NO_WARRANTY.getTypeTransaction() == transactionType){
            nameCardTransaction = network+" "+TypeCardTransaction.DEBIT_PRE_DATE_NO_WARRANTY.getName();
            return nameCardTransaction;
        }
        if(TypeCardTransaction.DEBIT_SIGHT.getTypeTransaction() == transactionType){
            nameCardTransaction = network+" "+TypeCardTransaction.DEBIT_SIGHT.getName();
            return nameCardTransaction;
        }
        if(TypeCardTransaction.DEBIT_SIGHT_FORCE.getTypeTransaction() == transactionType){
            nameCardTransaction = network+" "+TypeCardTransaction.DEBIT_SIGHT_FORCE.getName();
            return nameCardTransaction;
        }
        if(TypeCardTransaction.HEALTH_INSURANCE.getTypeTransaction() == transactionType){
            nameCardTransaction = network+" "+TypeCardTransaction.HEALTH_INSURANCE.getName();
            return nameCardTransaction;
        }
        if(TypeCardTransaction.OTHER.getTypeTransaction() == transactionType){
            nameCardTransaction = network+" "+TypeCardTransaction.OTHER.getName();
            return nameCardTransaction;
        }
        if(TypeCardTransaction.OTHER_CARDS.getTypeTransaction() == transactionType){
            nameCardTransaction = network+" "+TypeCardTransaction.OTHER_CARDS.getName();
            return nameCardTransaction;
        }
        if(TypeCardTransaction.PRE_AUTHORIZATION_CREDIT.getTypeTransaction() == transactionType){
            nameCardTransaction = network+" "+TypeCardTransaction.PRE_AUTHORIZATION_CREDIT.getName();
            return nameCardTransaction;
        }
        if(TypeCardTransaction.PRE_DATE_DEBT.getTypeTransaction() == transactionType){
            nameCardTransaction = network+" "+TypeCardTransaction.PRE_DATE_DEBT.getName();
            return nameCardTransaction;
        }
        if(TypeCardTransaction.QUERY_CDC.getTypeTransaction() == transactionType){
            nameCardTransaction = network+" "+TypeCardTransaction.QUERY_CDC.getName();
            return nameCardTransaction;
        }
        if(TypeCardTransaction.VOUCHER.getTypeTransaction() == transactionType){
            nameCardTransaction = network+" "+TypeCardTransaction.VOUCHER.getName();
            return nameCardTransaction;
        }
        return "";
    }
    
    private AdicionaNomeCartao() {
    }
}
