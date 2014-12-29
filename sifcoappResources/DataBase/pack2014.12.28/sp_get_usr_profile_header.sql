-- Function: sp_get_usr_profile_header(character varying)

-- DROP FUNCTION sp_get_usr_profile_header(character varying);

CREATE OR REPLACE FUNCTION sp_get_usr_profile_header(_nickname character varying)
  RETURNS SETOF refcursor AS
$BODY$
DECLARE
ref1 refcursor;
BEGIN

OPEN ref1 for SELECT prof.profilecode,prof.profilename, usr.usersign,usr.nickname
  FROM adm_usr1_profile prof, adm_usr0_user usr
  where prof.profilecode=usr.profilecode
  and usr.nickname=_nickname;

return NEXT ref1; 

END;
    $BODY$
  LANGUAGE plpgsql VOLATILE
  COST 100
  ROWS 1000;
ALTER FUNCTION sp_get_usr_profile_header(character varying)
  OWNER TO postgres;
