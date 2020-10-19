package ca.dal.bartertrader.vo;

public class Promise<T> {

    private Promise() {}

    public final static class Fulfilled<T> extends Promise<T> {
        private final T data;

        public Fulfilled(T data) {
            this.data = data;
        }

        public T getData() {
            return this.data;
        }
    }

    public final static class Rejected<T> extends Promise<Exception> {
        private final Exception error;
        private final String message;

        public Rejected(Exception error, String message) {
            this.error = error;
            this.message = message;
        }

        public String getMessage() {
            return message;
        }

        public Exception getError() {
            return error;
        }
    }

    public final static class Pending<T> extends Promise<T> {
        private final T data;

        public Pending(T data) {
            this.data = data;
        }

        public T getData() {
            return data;
        }
    }
}
