package br.com.eubusco.server.util;


/**
 * Utilitário responsável por retornar outro valor quando o objeto for nulo.
 *
 */
public class IfNull {
    
    private IfNull(){
        
    }

    /**
     * Quando o parâmetro "value" for igual a null o método deve retornar o valor do parâmetro "otherValue".
     * <br>Obs: O valor do parâmetro "otherValue" sempre deve ser do mesmo tipo do parâmetro "value".
     * <br>Ex 1: 
     * <br>String valor = null;
     * <br>String retorno = IfNull.get(valor,"");
     * <br>Ex 2: 
     * <br>Long valor = null;
     * <br>Long retorno = IfNull.get(valor,0L);
     * @param value
     * @param otherValue
     * @return
     */
    public static <T> T get(T value, T otherValue) {
        if (value == null) {
            return otherValue;
        }
        return value;
    }

}