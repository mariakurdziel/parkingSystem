package ejb.dto;
import java.io.Serializable;


    public class SpotStatus implements  Serializable{

        public SpotStatus() {
        }

        private long spot;

        /**
         * Get the value of spot
         *
         * @return the value of spot
         */
        public long getSpot() {
            return spot;
        }

        /**
         * Set the value of spot
         *
         * @param spot new value of spot
         */
        public void setSpot(long spot) {
            this.spot = spot;
        }


        private boolean reserved=false;
        private boolean paid=true;
        private String error;

        public String getError() {
            return error;
        }

        public void setError(String error) {
            this.error = error;
        }

        public boolean isReserved() {
            return reserved;
        }

        public void setReserved(boolean reserved) {
            this.reserved = reserved;
        }

        public boolean isPaid() {
            return paid;
        }

        public void setPaid(boolean paid) {
            this.paid = paid;
        }
    }


