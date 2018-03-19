/*
* Copyright 2018 Builders
*************************************************************
*Nome     : ConverterUtil.java
*Autor    : Builders
*Data     : Thu Mar 08 2018 00:02:30 GMT-0300 (-03)
*Empresa  : Platform Builders
*************************************************************
*/
package br.com.builders.treinamento.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.StringTokenizer;

import javax.swing.text.MaskFormatter;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;


public class ConverterUtil {

  private final static Logger LOG = LoggerFactory.getLogger(ConverterUtil.class);

  /**
   * Constante do formato de data e hora sem a barra e o ponto:
   * yyyy-MM-dd'T'HH:mm:ssX
   */
  public static final String FORMATO_DATA_HORA_ISO_8601 = "yyyy-MM-dd'T'HH:mm:ssX";

  /** Constante do formato da data sem a barra: ddMMyyyy. */
  public static final String FORMATO_DATA_PADRAO_SEM_BARRA = "ddMMyyyy";

  /** The Constant FORMATO_CPF. */
  public static final String FORMATO_CPF = "###.###.###-##";

  /** The Constant FORMATO_CNPJ. */
  public static final String FORMATO_CNPJ = "##.###.###/####-##";

  /** The Constant FORMATO_TELEFONE8. */
  public static final String FORMATO_TELEFONE8 = "####-####";

  /** The Constant FORMATO_TELEFONE9. */
  public static final String FORMATO_TELEFONE9 = "#####-####";

  /** The Constant FORMATO_TELEFONE10. */
  public static final String FORMATO_TELEFONE10 = "(##) ####-####";

  /** The Constant FORMATO_TELEFONE11. */
  public static final String FORMATO_TELEFONE11 = "(##) #####-####";

  /** Constante String CNPJ. */
  private static final String CNPJ = "CNPJ";

  private ConverterUtil() {}

  	/**
	 * Obtem a idade a partir de uma data de nascimento passada como parametro.
	 *
	 * @param nascimento
	 *            A data de nascimento para calculo da idade.
	 * @return A idade.
	 */
  public static int getIdade(final Date nascimento) {
    final Calendar cHoje = getDataHoraZerada(Calendar.getInstance());
    final Calendar cNascimento = Calendar.getInstance();
    cNascimento.setTime(getDataHoraZerada(nascimento));

    final Calendar cNascimentoTemp = Calendar.getInstance();
    cNascimentoTemp.setTime(cNascimento.getTime());

		// Se a data de nascimento for no dia 29 de fevereiro e o ano atual nao
		// for ano bissexto, a data e ajustada automaticamente para 01 de marco.
    cNascimentoTemp.set(Calendar.YEAR, cHoje.get(Calendar.YEAR));

    int idade = cHoje.get(Calendar.YEAR) - cNascimento.get(Calendar.YEAR);
    if (cHoje.before(cNascimentoTemp)) {
      idade--;
    }

    return idade;
  }

  	/**
	 * Retorna uma data, a partir da data passada como parametro, com os campos
	 * Hora, Minuto, Segundo e Milisegundos zerados.
	 *
	 * @param dataHora
	 *            A data a partir da qual sera gerada a nova data com os campos
	 *            Hora, Minuto, Segundo e Milisegundos zerados.
	 * @return A nova data com os campos Hora, Minuto, Segundo e Milisegundos
	 *         zerados.
	 */
  public static Calendar getDataHoraZerada(final Calendar dataHora) {
    final Calendar novaDataHora = Calendar.getInstance();
    novaDataHora.setTime(dataHora.getTime());

    novaDataHora.set(Calendar.HOUR_OF_DAY, 0);
    novaDataHora.set(Calendar.MINUTE, 0);
    novaDataHora.set(Calendar.SECOND, 0);
    novaDataHora.set(Calendar.MILLISECOND, 0);

    return novaDataHora;
  }

  	/**
	 * Retorna uma data, a partir da data passada como parametro, com os campos
	 * Hora, Minuto, Segundo e Milisegundos zerados.
	 *
	 * @param dataHora
	 *            A data a partir da qual sera gerada a nova data com os campos
	 *            Hora, Minuto, Segundo e Milisegundos zerados.
	 * @return A nova data com os campos Hora, Minuto, Segundo e Milisegundos
	 *         zerados.
	 */
  public static Date getDataHoraZerada(final Date dataHora) {
    final Calendar novaDataHora = Calendar.getInstance();
    novaDataHora.setTime(dataHora);

    return getDataHoraZerada(novaDataHora).getTime();
  }

  	/**
	 * Obtem a raiz do CNPJ.
	 *
	 * @param cnpj
	 *            O CNPJ a ter sua raiz obtida.
	 * @return A raiz do CNPJ.
	 */
  public static String getRaizCnpj(final String cnpj) {
		// O CNPJ deve possuir 14 digitos. Se nao possuir deve-se completar com
		// zeros e esquerda.
    final int dif = 14 - cnpj.length();

    final StringBuilder builder = new StringBuilder();
    for (int i = 0; i < dif; i++) {
      builder.append("0");
    }

    builder.append(cnpj);

    return builder.toString().substring(0, 8);
  }

  	/**
	 * Obtem a raiz do CNPJ.
	 *
	 * @param cnpj
	 *            O CNPJ a ter sua raiz obtida.
	 * @return A raiz do CNPJ.
	 */
  public static Long getRaizCnpj(final Long cnpj) {

    final Long divisor = 1000000L;
    return cnpj / divisor;

  }

  	/**
	 * Converte uma data do tipo {@link String} no formato especificado para o tipo
	 * {@link XMLGregorianCalendar}.
	 *
	 * @param data
	 *            A data a ser convertida
	 * @param formato
	 *            O formato da data a ser utilizado na conversao
	 * @return A data convertida ou <code>null</code>se uma excecaoo
	 *         {@link ParseException} ou {@link DatatypeConfigurationException}
	 *         ocorrer.
	 */
  public static XMLGregorianCalendar stringToXmlGregorianCalendar(final String data, final String formato) {
    XMLGregorianCalendar toReturn = null;

    try {
      final SimpleDateFormat sdf = new SimpleDateFormat(formato);

      if (!StringUtils.isEmpty(data)) {
        final Date date = sdf.parse(data);

        final GregorianCalendar cal = (GregorianCalendar) Calendar.getInstance();
        cal.setTime(date);

        toReturn = DatatypeFactory.newInstance().newXMLGregorianCalendar(cal).normalize();
      }
    } catch (final ParseException e) {
      LOG.error("Erro ao converter a String em Date.", e);
    } catch (final DatatypeConfigurationException e) {
      LOG.error("Implementa��o de DatatypeFactory n�o disponivel", e);
    }

    return toReturn;
  }

  	/**
	 * Converte uma data do tipo {@link XMLGregorianCalendar} no formato
	 * especificado para o tipo {@link String}.
	 *
	 * @param data
	 *            A data a ser convertida
	 * @param formato
	 *            O formato da data a ser utilizado na conversao
	 * @return A data convertida.
	 */
  public static String xmlGregorianCalendarToString(final XMLGregorianCalendar data, final String formato) {
    String toReturn = "";
    if (data != null && data.toGregorianCalendar() != null) {
      final SimpleDateFormat sdf = new SimpleDateFormat(formato);
      toReturn = sdf.format(data.toGregorianCalendar().getTime());
    }
    return toReturn;
  }

  /**
   * Remover formatacao cpf cnpj.
   *
   * @param nrDocumento the nr documento
   * @return the string
   */
  public static String removerFormatacaoCpfCnpj(final String nrDocumento) {
    String retorno = null;
    if (!StringUtils.isEmpty(nrDocumento)) {
      retorno = nrDocumento.replace(".", "").replace("-", "").replace("/", "");
    }
    return retorno;
  }

  /**
   * Remover formatacao cpf cnpj.
   *
   * @param data the data
   * @return the string
   */
  public static String removerFormatacaoData(final String data) {
    String retorno = null;
    if (!StringUtils.isEmpty(data)) {
      retorno = data.replace("/", "");
    }
    return retorno;
  }

  /**
   * Format string.
   *
   * @param mask the mask
   * @param value the value
   * @return the string
   */
  public static String formatString(final String mask, final String value) {
    try {
      final MaskFormatter formatter = new MaskFormatter(mask);
      formatter.setValueContainsLiteralCharacters(false);
      return formatter.valueToString(value);
    } catch (final ParseException e) {
      LOG.error(e.getMessage(), e);
    }
    return "";
  }

  /**
   * Desformat string.
   *
   * @param mask the mask
   * @param value the value
   * @return the string
   */
  public static String desformatString(final String mask, final String value) {
    try {
      final MaskFormatter desformatter = new MaskFormatter(mask);
      desformatter.setValueContainsLiteralCharacters(false);
      return desformatter.stringToValue(value).toString();
    } catch (final ParseException e) {
      LOG.error(e.getMessage(), e);
    }
    return "";
  }

  /**
   * Formata telefone.
   *
   * @param numero the numero
   * @return the string
   */
  public static String formataTelefone(final String numero) {
    String retorno = null;

    if (!StringUtils.isEmpty(numero)) {

      final int tamanho = numero.trim().length();

      switch (tamanho) {
        case 8:
          retorno = formatString(FORMATO_TELEFONE8, numero);
          break;
        case 9:
          retorno = formatString(FORMATO_TELEFONE9, numero);
          break;
        case 10:
          retorno = formatString(FORMATO_TELEFONE10, numero);
          break;
        case 11:
          retorno = formatString(FORMATO_TELEFONE11, numero);
          break;
        default:
          retorno = null;

      }

    }
    return retorno;
  }

  /**
   * Gets the bundle.
   *
   * @param key the key
   * @return the bundle
   */
  public static String getBundle(final String key) {
    final ResourceBundle bundle = ResourceBundle.getBundle("messages", Locale.getDefault());
    return bundle.getString(key);
  }

  	/**
	 * Obtem o numero do documento da empresa.
	 *
	 * @param tipoDocumento
	 *            O tipo do documento da empresa.
	 * @param numeroDocumento
	 *            O numero do documrento da empresa
	 * @return O numero do documento da empresa. Se tipoDocumento = CNPJ, retorna a
	 *         raiz do CNPJ, senao o numero completo.
	 */
  public static String getNumeroDocumentoEmpresa(final String tipoDocumento, final String numeroDocumento) {
    if (CNPJ.equalsIgnoreCase(tipoDocumento)) {
      return getRaizCnpj(numeroDocumento);
    }

    return numeroDocumento;
  }

  /**
   * Efetua o parse de uma String contendo Data e Hora no formato
   * "yyyy-MM-dd'T'HH:mm:ssX".
   *
   * @param dataHora A String a ser parseada.
   * @return A data parseada.
   * @throws ServiceException Quando ocorrer um erro ao efetuar o parse.
   */
  public static Date parseDataHoraPadrao(final String dataHora) throws IllegalArgumentException {
    final SimpleDateFormat sdf = new SimpleDateFormat(FORMATO_DATA_HORA_ISO_8601);
    try {
      return sdf.parse(dataHora);
    } catch (final ParseException e) {
			throw new IllegalArgumentException("Data e Hora inválida para o formato " + FORMATO_DATA_HORA_ISO_8601, e);
    }
  }

  /**
   * Efetua o parse de uma String contendo Data e Hora no formato solicitado.
   *
   * @param dataHora A String a ser parseada.
   * @param formato O formato a ser aplicado.
   * @return A data parseada.
   * @throws BusinessException the business exception
   */
  public static Date parseDataHora(final String dataHora, final String formato) throws IllegalArgumentException {
    final SimpleDateFormat sdf = new SimpleDateFormat(formato);
    try {
      return sdf.parse(dataHora);
    } catch (final ParseException e) {
			throw new IllegalArgumentException("Data e Hora inválida para o formato " + formato, e);
    }
  }

  	/**
	 * Obtem uma String de Data e Hora no formato fornecido.
	 *
	 * @param dataHora
	 *            A data a ser formatada.
	 * @param formato
	 *            O formato a ser aplicado.
	 * @return A data formatada.
	 */
  public static String formatDataHora(final Date dataHora, final String formato) {
    if (dataHora == null) {
      return null;
    }

    final SimpleDateFormat sdf = new SimpleDateFormat(formato);
    return sdf.format(dataHora);
  }

  	/**
	 * Obtem uma String de Data e Hora no formato "yyyy-MM-dd'T'HH:mm:ssX". * @param
	 * dataHora A data a ser formatada.
	 *
	 * @param dataHora
	 *            the data hora
	 * @return A data formatada.
	 */
  public static String formatDataHoraPadrao(final Date dataHora) {
    if (dataHora == null) {
      return null;
    }

    final SimpleDateFormat sdf = new SimpleDateFormat(FORMATO_DATA_HORA_ISO_8601);
    return sdf.format(dataHora);
  }

  /**
   * Devolve um array de string, retirando os delimitadores. Caso nenhum
   * delimitador seja passado, utiliza o default <code>" " [espao]</code
   *
   * @param texto the texto
   * @param delimitador the delimitador
   * @return the array
   */
  public final static String[] getArray(final String texto, final String delimitador) {
    if (StringUtils.isEmpty(texto)) {
      return new String[0];
    }

    int i = 0;
    StringTokenizer tokenizer;
    if (delimitador != null) {
      tokenizer = new StringTokenizer(texto, delimitador);
    } else {
      tokenizer = new StringTokenizer(texto);
    }

    final String[] retorno = new String[tokenizer.countTokens()];
    while (tokenizer.hasMoreTokens()) {
      retorno[i] = tokenizer.nextToken();
      i++;
    }

    return retorno;
  }

  /**
   * Obtem locale pt br.
   *
   * @return the locale
   */
  public static Locale localePtBr() {
    return new Locale("pt", "BR");
  }

}
