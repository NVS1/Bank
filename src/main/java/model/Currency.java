package model;

public enum Currency {
    USD {
        @Override
        public String toString() {
            return "USD";
        }
    },
    EUR{
        @Override
        public String toString() {
            return "EUR";
        }
    },
    UAH{
        @Override
        public String toString() {
            return "UAH";
        }
    }
}
