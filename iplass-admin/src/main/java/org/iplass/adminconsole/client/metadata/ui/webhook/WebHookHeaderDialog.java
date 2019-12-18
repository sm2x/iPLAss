package org.iplass.adminconsole.client.metadata.ui.webhook;

import java.util.ArrayList;
import java.util.List;

import org.iplass.adminconsole.client.base.event.DataChangedEvent;
import org.iplass.adminconsole.client.base.event.DataChangedHandler;
import org.iplass.adminconsole.client.base.ui.widget.MtpDialog;
import org.iplass.adminconsole.client.base.ui.widget.form.MtpForm2Column;
import org.iplass.adminconsole.client.base.util.SmartGWTUtil;
import org.iplass.mtp.webhook.template.definition.WebHookHeader;

import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.data.fields.DataSourceTextField;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.ComboBoxItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.form.validator.CustomValidator;
import com.smartgwt.client.widgets.layout.VLayout;

/**
 * @author lisf06
 *
 */
public class WebHookHeaderDialog extends MtpDialog {
	private HeaderAttributePane headerAttrEditPane;
	
	private WebHookHeader curHeaderDefinition;
	
	/** データ変更ハンドラ */
	private List<DataChangedHandler> handlers = new ArrayList<DataChangedHandler>();
	
	
	public WebHookHeaderDialog(WebHookHeader headerDefinition, ArrayList<WebHookHeader> headers) {
		curHeaderDefinition = headerDefinition;
		if (curHeaderDefinition==null) {
			curHeaderDefinition = new WebHookHeader();
		}
		
		setTitle("Header Editor");
		setHeight("20%");
		centerInPage();
		
		headerAttrEditPane = new HeaderAttributePane();
		headerAttrEditPane.setHeight100();
		
		container.addMember(headerAttrEditPane);
		
		IButton save = new IButton("Save");
		save.addClickHandler(new com.smartgwt.client.widgets.events.ClickHandler() {
			public void onClick(com.smartgwt.client.widgets.events.ClickEvent event) {
				WebHookHeader definition = curHeaderDefinition;
				//TODO: prevent clicking if header is duplicated
				definition = headerAttrEditPane.getEditDefinition(definition);
				fireDataChanged(definition);
				destroy();
			}
		});

		IButton cancel = new IButton("Cancel");
		cancel.addClickHandler(new com.smartgwt.client.widgets.events.ClickHandler() {
			public void onClick(com.smartgwt.client.widgets.events.ClickEvent event) {
				destroy();
			}
		});
		footer.setMembers(save, cancel);
		
	}
	private class HeaderAttributePane extends VLayout {
		private DynamicForm form;
		private TextItem headerNameField;
		private TextItem headerValueField;
		
		public HeaderAttributePane() {
			form = new MtpForm2Column();
			headerNameField = new TextItem("headerName", "Header");
			headerValueField = new TextItem("headerValue","Value");
			
			form.setItems(headerNameField, headerValueField);
			addMember(form);
			setDefinition(curHeaderDefinition);
		}
		
		/** definition -> dialog */
		public void setDefinition(WebHookHeader _curHeaderDefinition) {
			if ( _curHeaderDefinition!=null) {
				headerNameField.setValue(_curHeaderDefinition.getKey());
				headerValueField.setValue(_curHeaderDefinition.getValue());
			} else {
				headerNameField.clearValue();
				headerValueField.clearValue();
			}
		}
		
		/** dialog -> definition */
		public WebHookHeader getEditDefinition(WebHookHeader _curHeaderDefinition){
			_curHeaderDefinition.setKey(SmartGWTUtil.getStringValue(headerNameField));
			_curHeaderDefinition.setValue(SmartGWTUtil.getStringValue(headerValueField));
			return _curHeaderDefinition;
		}
	}
	
	/**
	 * {@link DataChangedHandler} を追加します。
	 *
	 * @param handler {@link DataChangedHandler}
	 */
	public void addDataChangeHandler(DataChangedHandler handler) {
		handlers.add(0, handler);
	}
	
	/**
	 * データ変更通知処理
	 */
	private void fireDataChanged(WebHookHeader paramMap) {
		DataChangedEvent event = new DataChangedEvent();
		event.setValueObject(paramMap);
		for (DataChangedHandler handler : handlers) {
			handler.onDataChanged(event);
		}
	}
}
