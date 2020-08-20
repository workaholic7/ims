package com.mandeep.ims.entity;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;
import org.springframework.beans.factory.annotation.Value;

public class ReferenceNumGenerator implements IdentifierGenerator {

	@Value("${reference.number.prefix}")
	String prefix;

	@Override
	public String generate(SharedSessionContractImplementor session, Object object) throws HibernateException {
		Connection con = session.connection();
		String generatedRefNum = null;
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select top 1 id from invoice order by id desc");
			if (rs.next()) {
				int id = rs.getInt(1) + 1;
				generatedRefNum = prefix + id + LocalDate.now().getMonthValue() + LocalDate.now().getYear();
				return generatedRefNum;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
