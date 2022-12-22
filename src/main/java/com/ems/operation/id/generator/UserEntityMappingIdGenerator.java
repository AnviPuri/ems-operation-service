package com.ems.operation.id.generator;

import java.io.Serializable;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

import com.ems.operation.constant.Constants;

import ems.utility.util.EmsUtility;

public class UserEntityMappingIdGenerator implements IdentifierGenerator {

	@Override
	public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {
		String prefix = Constants.IdPrefix.USEN;
		String departmentId = EmsUtility.createUniqueId(prefix);
		return departmentId;
	}

}
