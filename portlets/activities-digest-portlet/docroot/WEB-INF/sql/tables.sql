create table Digest_DigestConfiguration (
	id_ LONG not null primary key,
	companyId LONG,
	createDate DATE null,
	modifiedDate DATE null,
	groupId LONG,
	userId LONG,
	enabled BOOLEAN,
	frequency INTEGER,
	scopeGroupId LONG,
	scopeUserId LONG,
	summaryLength INTEGER,
	activityTypes STRING null
);

create table Digest_UserDigestConfiguration (
	id_ LONG not null primary key,
	companyId LONG,
	createDate DATE null,
	modifiedDate DATE null,
	userId LONG,
	frequency INTEGER
);