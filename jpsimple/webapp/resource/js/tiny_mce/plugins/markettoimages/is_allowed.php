<?php

/*-------------------------------------------------------------------
|
| In case, when TinyMCE’s folder is not protected with HTTP Authorisation,
| you should require is_allowed()  function to return 
| `TRUE` if user is authorised,
| `FALSE` - otherwise. 
| 
| This would protect upload script, if someone guesses it's url.
| 
-------------------------------------------------------------------*/

function is_allowed()
{
	return TRUE;
}

?>