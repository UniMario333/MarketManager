/*
 * Progetto Università Parthenope di Programmazione 3
 * Mario Daniele 0124001808.
 */
package it.uniparthenope.prog3.daniele.mario.marketmanager.model.builder.category;

/**
 * Rappresenta una categoria.
 *
 * @author Mario Daniele 0124001808
 * @since 1.0
 * @version 1.0
 */
public class Category {

    /** Nome della categoria. */
    private String name;

    /** Descrizione della categoria */
    private String description;

    /**
     * Ottiene il nome categoria.
     *
     * @return il nome della categoria
     */
    public String getName() {
        
        return name;
        
    }

    /**
     * Ottiene la descrizione della categoria.
     *
     * @return la descrizione della categoria
     */
    public String getDescription() {
        
        return description;
        
    }
    
    /**
     * Il concrete builder per le categorie.
     * 
     * @author Mario Daniele 0124001808
     * @since 1.0
     * @version 1.0
     */
    public static class CategoryBuilder {
        
        /** Nome della categoria. */
        private String name;

        /** Descrizione della categoria */
        private String description;
        
        /**
         * Costruttore del builder.
         * 
         * @param pName il nome della categoria
         */
        public CategoryBuilder(String pName) {
            
            this.name = "";
            this.description = "";
            
        }

        /**
         * Costruisce un oggetto categoria.
         * 
         * @return un oggetto categoria
         */
        public Category build(){
                     
            Category category = new Category();
                     
            category.name = this.name;
            category.description = this.description;
                     
            return category;
                     
        }

        /**
         * Imposta il nome della categoria.
         * 
         * @param pName il nuovo nome della categoria
         * @return il nuovo nome della categoria
         */
        public CategoryBuilder setName(String pName) {
                     
            this.name = pName;
                     
            return this;
                     
        }
        
        /**
         * Imposta la descrizione della categoria.
         * 
         * @param pDescription la nuova descrizione della categoria
         * @return la nuova descrizione della categoria
         */
        public CategoryBuilder setDescription(String pDescription) {
                     
            this.description = pDescription;
                     
            return this;
                     
        }
    
    }

    @Override
    public String toString() {
        
        return getName() + " " + getDescription();
        
    }
    
}
