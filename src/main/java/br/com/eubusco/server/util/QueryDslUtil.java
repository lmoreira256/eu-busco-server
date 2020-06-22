package br.com.eubusco.server.util;

import java.util.Date;

import com.mysema.query.types.ConstantImpl;
import com.mysema.query.types.Expression;
import com.mysema.query.types.expr.StringExpression;
import com.mysema.query.types.template.StringTemplate;

public class QueryDslUtil {

	private QueryDslUtil() {
	}

	public static StringExpression dataString(Expression<Date> data, String formato) {
		return StringTemplate.create("to_char({0},'{1s}')", data, ConstantImpl.create(formato));
	}

}
