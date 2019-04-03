package ru.homecompany.bank.utils;

public class ResponseErrorView implements MyResponse {

    private String error;

    private ResponseErrorView() {
        // private constructor
    }

    public static Creator newCreator() {
        return new ResponseErrorView().new Creator();
    }

    public String getError() {
        return error;
    }

    @Override
    public String toString() {
        return "ResponseErrorView{" +
                "error='" + error + '\'' +
                '}';
    }

    public class Creator {

        private Creator() {
            // private constructor
        }

        public Creator setError(String error) {
            ResponseErrorView.this.error = error;

            return this;
        }

        public ResponseErrorView create() {
            return ResponseErrorView.this;
        }
    }

}
