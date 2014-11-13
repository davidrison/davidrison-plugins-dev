create index IX_3624EB26 on Digest_DigestConfiguration (companyId, enabled);
create index IX_14A853E5 on Digest_DigestConfiguration (companyId, scopeGroupId, scopeUserId);
create index IX_D3002BEC on Digest_DigestConfiguration (companyId, scopeGroupId, scopeUserId, enabled);
create index IX_BDB30107 on Digest_DigestConfiguration (companyId, scopeGroupId, scopeUserId, frequency);
create index IX_1ED1DD57 on Digest_DigestConfiguration (scopeGroupId);
create index IX_6E857C55 on Digest_DigestConfiguration (scopeGroupId, frequency);
create index IX_991AA5ED on Digest_DigestConfiguration (scopeUserId);
create index IX_5755A2E4 on Digest_DigestConfiguration (scopeUserId, enabled);
create index IX_828699FF on Digest_DigestConfiguration (scopeUserId, frequency);

create index IX_CAFAA9D6 on Digest_UserDigestConfiguration (companyId);
create index IX_F0414C on Digest_UserDigestConfiguration (userId);