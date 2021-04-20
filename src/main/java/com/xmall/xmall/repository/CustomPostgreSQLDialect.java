package com.xmall.xmall.repository;

import org.hibernate.dialect.PostgreSQL10Dialect;
import org.hibernate.dialect.function.SQLFunctionTemplate;
import org.hibernate.dialect.function.StandardSQLFunction;
import org.hibernate.type.StandardBasicTypes;

public class CustomPostgreSQLDialect extends PostgreSQL10Dialect {

    public CustomPostgreSQLDialect() {
        super();
        // postgresql 내장함수 + 사용자 정의 함수 등록

        // DB 내장함수 사용
        // postgresql CONCAT_WS
        this.registerFunction("CONCAT_WS", new StandardSQLFunction("CONCAT_WS", StandardBasicTypes.STRING));

        // 사용자 정의함수의 경우 아래와 같은 방식을 응용하면 된다.
        // 예를 들어 스트링형태의 리턴타입 함수는 StandardBasicTypes.STRING로 선언해준다.
        this.registerFunction("사용할 함수명", new SQLFunctionTemplate(StandardBasicTypes.STRING, "사용할 함수명(?1)"));

        this.registerFunction( "dayofweek", new SQLFunctionTemplate(StandardBasicTypes.INTEGER, "extract(dow from ?1)") );
    }

}
