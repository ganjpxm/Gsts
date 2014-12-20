<?php
class Editor extends Controller
{
	function Editor()
	{
		$this->_construct();
	}
	function _construct()
	{
		parent::Controller();
		$this->load->helper('markettoimages');
		
		if (is_allowed() === FALSE)
		{
			exit;
		}
		
		$langs = array('russian','english');
		
		$this->config->load('uploader_settings', TRUE);
		$lang = $this->config->item('lang', 'uploader_settings');
		
		if (!in_array($lang, $langs))
		{
			$lang = 'english';
		}
		
		$this->config->set_item('language', $lang);
	}
		
	function upload()
	{		
		$conf['img_path'] = $this->config->item('img_path', 'uploader_settings');
		$conf['allow_resize'] = $this->config->item('allow_resize', 'uploader_settings');
		
		$config['allowed_types'] = $this->config->item('allowed_types', 'uploader_settings');
		$config['max_size'] = $this->config->item('max_size', 'uploader_settings');
		$config['encrypt_name'] = $this->config->item('encrypt_name', 'uploader_settings');
		$config['latinize_name'] = $this->config->item('latinize_name', 'uploader_settings');
		$config['overwrite'] = $this->config->item('overwrite', 'uploader_settings');
		$config['upload_path'] = $this->config->item('upload_path', 'uploader_settings');
		
		if (!$conf['allow_resize'])
		{
			$config['max_width'] = $this->config->item('max_width', 'uploader_settings');
			$config['max_height'] = $this->config->item('max_height', 'uploader_settings');
		}
		else
		{
			$conf['max_width'] = $this->config->item('max_width', 'uploader_settings');
			$conf['max_height'] = $this->config->item('max_height', 'uploader_settings');
			
			if ($conf['max_width'] == 0 and $conf['max_height'] == 0)
			{
				$conf['allow_resize'] = FALSE;
			}
		}
		
		$this->load->library('upload', $config);
		
		if ($this->upload->do_upload())
		{
			$result = $this->upload->data();
			
			if ($conf['allow_resize'] and (($result['image_width'] > $conf['max_width'] and $conf['max_width'] > 0) or ($result['image_height'] > $conf['max_height'] and $conf['max_height'] > 0)))
			{
				if ($conf['max_height'] == 0)
				{				
					$aspect_ratio = $result['image_width'] / $result['image_height'];
					$new_width = $conf['max_width'];
					$new_height = floor($new_width / $aspect_ratio);
				}
				elseif ($conf['max_width'] == 0)
				{				
					$aspect_ratio = $result['image_height'] / $result['image_width'];
					$new_height = $conf['max_height'];
					$new_width = floor($new_height / $aspect_ratio);
				}
				else
				{
					$new_width = $conf['max_width'];
					$new_height = $conf['max_height'];
				}
				
				$resizeParams = array
				(
					'source_image'=>$result['full_path'],
					'width'=>$new_width,
					'height'=>$new_height
				);
				$this->load->library('image_lib', $resizeParams);
				$this->image_lib->resize();
			}
			
			/* //make thumbnail for image manager (in newer versions)
			$resizeParams = array
			(
				'source_image'=>$result['full_path'],
				'new_image'=>$result['file_path'] . '/t_' . $result['file_name'],
				'width'=>25,
				'height'=>25
			);
			$this->load->library('image_lib', $resizeParams);
			$this->image_lib->initialize($resizeParams); //in case image_lib is already loaded
			$this->image_lib->resize(); */
			
			$result['result'] = "Файл загружен";
			$result['resultcode'] = 'ok';
			$result['file_name'] = $conf['img_path'] . '/' . $result['file_name'];
			$this->load->view('ajax_upload_result', $result);
		}
		else
		{
			$result['result'] = $this->upload->display_errors(' ', ' ');
			$result['resultcode'] = 'failed';
			$this->load->view('ajax_upload_result', $result);
		}
	}
	
	function dir()
	{
		$this->load->helper('file');
		
		$this->config->load('uploader_settings', TRUE);
		
		$d['upload_path'] = $this->config->item('upload_path', 'uploader_settings');
		$d['img_path'] = $this->config->item('img_path', 'uploader_settings');
		
		$d['files'] = get_dir_file_info($d['upload_path']);
		
		$this->load->view('file_list', $d);
	}
	
	function blank()
	{
		$this->load->view('blank');
	}
	
}