/*
* Copyright 2018 Builders
*************************************************************
*Nome     : ValidationConstants.java
*Autor    : Builders
*Data     : Thu Mar 08 2018 00:02:30 GMT-0300 (-03)
*Empresa  : Platform Builders
*************************************************************
*/
package br.com.builders.treinamento.utils;

public class ValidationConstants {

	/** requiredField */
	public static final String MSG_VERSAO_SERVICO_NAO_INFORMADA = "requiredField.version";

	/** businessError.creating.order */
	public static final String MSG_SAP_WEB_SERVICE_ERROR_CREATING_ORDER = "businessError.creating.order";

	/**
	 * Pattern no formato ISO 8601 - YYYY-MM-DDThh:mm:ssTZD. Exemplo:
	 * 1997-07-16T19:20:30+01:00
	 */

	public static final String PATTERN_VALIDATION_DATE_TIME = "^([\\+-]?\\d{4}(?!\\d{2}\\b))((-?)((0[1-9]|1[0-2])(\\3([12]\\d|0[1-9]|3[01]))?|W([0-4]\\d|5[0-2])(-?[1-7])?|(00[1-9]|0[1-9]\\d|[12]\\d{2}|3([0-5]\\d|6[1-6])))([T\\s]((([01]\\d|2[0-3])((:?)[0-5]\\d)?|24\\:?00)([\\.,]\\d+(?!:))?)?(\\17[0-5]\\d([\\.,]\\d+)?)?([zZ]|([\\+-])([01]\\d|2[0-3]):?([0-5]\\d)?)?)?)?$";
	
	public static final String PATTERN_VALIDATION_DATE = "^\\d{2}/\\d{2}/\\d{4}$";

	public static final String PATTERN_NUMBER_DIGITS = "^[0-9]*$";

	public static final String PATTERN_MAX_INSTALLMENTS = "^([1-9]|[1][0-2])$";

	public static final String PATTERN_NUMBER_DIGITS_GREATER_ZERO = "^[1-9][0-9]*$";

	/** String CPF case Insensitive */
	public static final String PATTERN_VALIDATION_CPF_KIND_DOMAIN = "(^cpf$)";

	/** (^cellphone$)|(^home$)|(^business$) */
	public static final String PATTERN_VALIDATION_PHONE_KIND_DOMAIN = "(^cellphone$)|(^home$)|(^business$)";

	/** Validar tipo de Pagamento */
  public static final String PATTERN_VALIDATION_TIPOSERVICO_KIND_DOMAIN =
      "(^BOLETO$)|(^ContaConcessionaria$)|(^FichaCompensacao$)|(^INDEFINIDO$)";

	/**
	 * Pattern para as 27 unidades federativas, sendo 26 estados e 1 distrito
	 * federal.
	 */

	public static final String PATTERN_VALIDATION_BRAZILIAN_STATES_DOMAIN = "(^ac$)|(^al$)|(^ap$)|(^am$)|(^ba$)|(^ce$)|(^df$)|(^es$)|(^go$)|(^ma$)|(^mt$)|(^ms$)|(^mg$)|(^pa$)|(^pb$)|(^pr$)|(^pe$)|(^pi$)|(^rj$)|(^rn$)|(^rs$)|(^ro$)|(^rr$)|(^sc)|(^sp$)|(^se$)|(^to$)";

	/**
	 * Pattern de CEP (formato XXXXX-XXX ou XXXXXXXX)
	 */
	public static final String PATTERN_VALIDATION_CEP = "(\\d{5}-\\d{3})|(\\d{8})";

	/**
	 * Pattern de CEP (XXXXXXXX)
	 */
	public static final String PATTERN_VALIDATION_CEP_NUMBER_ONLY = "[0-9]{8}";

	/**
	 * Pattern para números de telefone, o qual não deve iniciar com zero e deve ter
	 * entre 10 e 11 digitos. ^[1-9]{2}[0-9]{4,5}[0-9]{4}$
	 */
	public static final String PATTERN_VALIDATION_PHONE_NUMBER_DOMAIN = "^[1-9]{2}[0-9]{4,5}[0-9]{4}$";

	/** (^cancel$)|(^redirect$)|(^notification$) */
	public static final String PATTERN_VALIDATION_URL_KIND_DOMAIN = "(^cancel$)|(^redirect$)|(^notification$)|(^orderNotification$)";

	/**
	 * ^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\
	 * .[A-Za-z]{2,})$
	 */
	public static final String PATTERN_VALIDATION_EMAIL = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

	public static final String PATTERN_TRACKING_NUMBER = "[A-Za-z]{2}[0-9]{9}[A-Za-z]{2}";

	public static final String PATTERN_NUMBER = "^\\d+$";

	public static final String PATTERN_BOOLEAN = "^([Tt][Rr][Uu][Ee]|[Ff][Aa][Ll][Ss][Ee])$";

	public static final String CPF_INVALID = "Invalid CPF";

	public static final String EMAIL_INVALID = "Invalid E-mail";

	public static final String GENERIC_ERROR = "Generic Error";

	private ValidationConstants() {

	}

}
