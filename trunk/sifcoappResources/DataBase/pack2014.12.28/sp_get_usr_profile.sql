-- Function: sp_get_usr_profile(integer)

-- DROP FUNCTION sp_get_usr_profile(integer);

CREATE OR REPLACE FUNCTION sp_get_usr_profile(_profilecode integer)
  RETURNS SETOF refcursor AS
$BODY$
DECLARE
ref1 refcursor;

BEGIN
OPEN ref1 for SELECT det.profilecode, profiledetcode, profiledetparent, profiledetdesc, 
       profiledeturl, profiledeticon, prof.profilename
  FROM adm_usr2_profile_det det, adm_usr1_profile prof
  where det.profilecode=prof.profilecode
  and det.profilecode=_profilecode;

return NEXT ref1; 

END;
    $BODY$
  LANGUAGE plpgsql VOLATILE
  COST 100
  ROWS 1000;
ALTER FUNCTION sp_get_usr_profile(integer)
  OWNER TO postgres;
