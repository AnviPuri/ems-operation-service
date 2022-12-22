package com.ems.operation.util;

import com.ems.operation.entity.Audit;

import ems.utility.util.EmsUtility;

public class AuditUtility {

	public static Audit createApiAuditBuild() {

		Audit audit = new Audit();
		audit.setCreatedAt(EmsUtility.getEpochTimeInMillis());
		audit.setActive(true);
		// set created by later
		return audit;
	}

	public static Audit updateApiAuditBuild(Audit audit) {

		audit.setUpdatedAt(EmsUtility.getEpochTimeInMillis());
		// set updated by later
		return audit;
	}

	public static Audit deleteApiAuditBuild(Audit audit) {

		audit.setDeletedAt(EmsUtility.getEpochTimeInMillis());
		audit.setActive(false);
		// set deleted by later
		return audit;
	}

}
