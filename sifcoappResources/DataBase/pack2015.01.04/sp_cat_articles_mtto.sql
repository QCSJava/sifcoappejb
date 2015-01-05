-- Function: sp_cat_articles_mtto(character varying, character varying, character varying, character varying, character, character varying, character, character, character, character, character varying, character varying, double precision, character, double precision, character varying, double precision, double precision, double precision, character, date, date, character varying, double precision, character varying, character, character varying, character varying, integer, integer)

-- DROP FUNCTION sp_cat_articles_mtto(character varying, character varying, character varying, character varying, character, character varying, character, character, character, character, character varying, character varying, double precision, character, double precision, character varying, double precision, double precision, double precision, character, date, date, character varying, double precision, character varying, character, character varying, character varying, integer, integer);

CREATE OR REPLACE FUNCTION sp_cat_articles_mtto(_itemcode character varying, _itemname character varying, _itemtype character varying, _itmsgrpcod character varying, _vatliable character, _codebars character varying, _prchseitem character, _sellitem character, _invntitem character, _assetitem character, _cardcode character varying, _buyunitmsr character varying, _numinbuy double precision, _salunitmsr character, _salpackun double precision, _suppcatnum character varying, _purpackun double precision, _avgprice double precision, _onhand double precision, _validfor character, _validfrom date, _validto date, _invntryuom character varying, _numinsale double precision, _dfltwh character varying, _wtliable character, _sww character varying, _validcomm character varying, _usersign integer, _action integer)
  RETURNS void AS
$BODY$

BEGIN

IF _action=1 then
	INSERT INTO cat_art0_articles(
            itemcode, itemname, itemtype, itmsgrpcod, vatliable, codebars, 
            prchseitem, sellitem, invntitem, assetitem, cardcode, buyunitmsr, 
            numinbuy, salunitmsr, salpackun, suppcatnum, purpackun, avgprice, 
            onhand, validfor, validfrom, validto, invntryuom, numinsale, 
            dfltwh, wtliable, sww, validcomm, usersign, createdate, createtime)
	VALUES (_itemcode,
            _itemname,
            _itemtype,
            _itmsgrpcod,
            _vatliable,
            _codebars,
            _prchseitem,
            _sellitem,
            _invntitem,
            _assetitem,
            _cardcode,
            _buyunitmsr,
            _numinbuy,
            _salunitmsr,
            _salpackun,
            _suppcatnum,
            _purpackun,
            _avgprice,
            _onhand,
            _validfor,
            _validfrom,
            _validto,
            _invntryuom,
            _numinsale,
            _dfltwh,
            _wtliable,
            _sww,
            _validcomm,
            _usersign,
            current_date,
            to_number (to_char (now (), 'HH24MI'), '9999'));
end if;
IF _action=2 THEN
	UPDATE cat_art0_articles
	   SET itemname = _itemname,
	       itemtype = _itemtype,
	       itmsgrpcod = _itmsgrpcod,
	       vatliable = _vatliable,
	       codebars = _codebars,
	       prchseitem = _prchseitem,
	       sellitem = _sellitem,
	       invntitem = _invntitem,
	       assetitem = _assetitem,
	       cardcode = _cardcode,
	       buyunitmsr = _buyunitmsr,
	       numinbuy = _numinbuy,
	       salunitmsr = _salunitmsr,
	       salpackun = _salpackun,
	       suppcatnum = _suppcatnum,
	       purpackun = _purpackun,
	       validfor = _validfor,
	       validfrom = _validfrom,
	       validto = _validto,
	       invntryuom = _invntryuom,
	       numinsale = _numinsale,
	       dfltwh = _dfltwh,
	       wtliable = _wtliable,
	       sww = _sww,
	       validcomm = _validcomm,
	       usersign = _usersign,
	       createdate = current_date,
	       createtime = to_number (to_char (now (), 'HH24MI'), '9999')
	 WHERE itemcode = _itemcode;
END IF;
IF _action=3 then
DELETE from cat_art0_articles where itemcode = _itemcode;
end if;

END; 

$BODY$
  LANGUAGE plpgsql VOLATILE
  COST 100;
ALTER FUNCTION sp_cat_articles_mtto(character varying, character varying, character varying, character varying, character, character varying, character, character, character, character, character varying, character varying, double precision, character, double precision, character varying, double precision, double precision, double precision, character, date, date, character varying, double precision, character varying, character, character varying, character varying, integer, integer)
  OWNER TO postgres;
