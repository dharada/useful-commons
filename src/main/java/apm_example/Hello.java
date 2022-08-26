package apm_example;

import co.elastic.apm.api.ElasticApm;
import co.elastic.apm.api.Transaction;

class Hello {
    public static void main(String[] args) {
        Transaction transaction = ElasticApm.startTransaction();
        try {
            transaction.setName("MyController#myAction");
            transaction.setType(Transaction.TYPE_REQUEST);

            // do your thing...
            System.out.println("Hello World");

        } catch (Exception e) {
            transaction.captureException(e);
            throw e;
        } finally {
            transaction.end();
        }
    }
}
