-- Function: sp_get_acc0_account(character varying, character varying, character varying)

-- DROP FUNCTION sp_get_acc0_account(character varying, character varying, character varying);

CREATE OR REPLACE FUNCTION sp_get_acc0_account(_acctcode character varying, _acctname character varying, _postable character varying)
  RETURNS SETOF refcursor AS
$BODY$
DECLARE
ref1 refcursor;
sql character varying(5000);
condition character varying(10);

BEGIN

if _postable IS NOT NULL THEN

sql := 'SELECT acctcode, acctname, currtotal, endtotal, finanse, budget, postable, 
       levels, grpline, fathernum, groupmask, intrmatch, acttype, protected, 
       createdate, updatedate, usersign, objtype, validfor, formatcode
	FROM cat_acc0_account Where postable=''Y''';

condition:=' AND ';

END IF;

if _postable IS NULL THEN

sql := 'SELECT acctcode, acctname, currtotal, endtotal, finanse, budget, postable, 
       levels, grpline, fathernum, groupmask, intrmatch, acttype, protected, 
       createdate, updatedate, usersign, objtype, validfor, formatcode
	FROM cat_acc0_account ';

condition:=' WHERE ';

END IF;

IF _acctcode IS NOT NULL THEN
	sql := sql || condition || 'acctcode ~*'''|| _acctcode ||'''';
	--condition = ' AND ';
END IF;

IF _acctname IS NOT NULL THEN
	if _postable IS NULL THEN
		condition = ' WHERE ';
		IF _acctcode IS NOT NULL then
			condition = ' AND ';
		END IF;
	ELSE
		condition = ' AND ';
	END IF;

	sql := sql || condition || 'acctname ~*'''|| _acctname ||'''';
	--condition = ' AND ';
	
END IF;

--sql := sql || condition || 'order by acctcode asc';
sql := sql  || 'order by acctcode asc';
	
OPEN ref1 FOR execute sql;

return NEXT ref1 ;

END;
    $BODY$
  LANGUAGE plpgsql VOLATILE
  COST 100
  ROWS 1000;
ALTER FUNCTION sp_get_acc0_account(character varying, character varying, character varying)
  OWNER TO postgres;
