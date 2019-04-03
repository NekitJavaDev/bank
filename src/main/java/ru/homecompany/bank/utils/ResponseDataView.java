package ru.homecompany.bank.utils;

public class ResponseDataView implements MyResponse {

    private Object data;

    private ResponseDataView() {
    }

    public static Creator newCreator() {
        return new ResponseDataView().new Creator();
    }

    public Object getData() {
        return data;
    }

    @Override
    public String toString() {
        return "ResponseDataView{" +
                "data=" + data +
                '}';
    }

    public class Creator {

        private Creator() {
        }

        public Creator setData(Object data) {
            ResponseDataView.this.data = data;
            return this;
        }

        public ResponseDataView create() {
            return ResponseDataView.this;
        }
    }
}
