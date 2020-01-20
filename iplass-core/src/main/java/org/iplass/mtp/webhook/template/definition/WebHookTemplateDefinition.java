/*
 * Copyright (C) 2020 INFORMATION SERVICES INTERNATIONAL - DENTSU, LTD. All Rights Reserved.
 * 
 * Unless you have purchased a commercial license,
 * the following license terms apply:
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License
 * along with this program. If not, see <https://www.gnu.org/licenses/>.
 */
package org.iplass.mtp.webhook.template.definition;

import java.util.ArrayList;
import javax.xml.bind.annotation.XmlRootElement;
import org.iplass.mtp.definition.Definition;

@XmlRootElement
public class WebHookTemplateDefinition implements Definition {

	private static final long serialVersionUID = 4835431145639526016L;
	private String metaDataId;
	
	private String name;
	private String displayName;
	private String description;
	
	private WebHookContent contentBody;
	private String sender;
	private String addressUrl;
	private String tokenHeader;//セキュリテぃトークンのヘッダー名を設置
	private String httpMethod;

	private Boolean synchronous;

	/** サブスクライバー：このwebhookを要求した方達 */
	private ArrayList<WebHookSubscriber> subscribers;

	/** headers */
	private ArrayList<WebHookHeader> headers;
	


	/**　リトライ関連　*/
	/** 失敗したらやり直ししますか */
	private boolean retry;
	/** やり直しの最大回数 */
	private int retryLimit;
	/** やり直す度の待ち時間(ms)*/
	private int retryInterval;
	
	
	public WebHookTemplateDefinition() {
	}

	public WebHookContent getContentBody() {
		if (contentBody == null) {
			this.contentBody=new WebHookContent();
		}
		return contentBody;
	}

	public void setContentBody(WebHookContent contentBody) {
		this.contentBody = contentBody;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getAddressUrl() {
		return addressUrl;
	}

	public void setAddressUrl(String addressUrl) {
		this.addressUrl = addressUrl;
	}

	public boolean isRetry() {
		return retry;
	}

	public void setRetry(boolean retry) {
		this.retry = retry;
	}

	public int getRetryLimit() {
		return retryLimit;
	}

	public void setRetryLimit(int retryLimit) {
		this.retryLimit = retryLimit;
	}

	public int getRetryInterval() {
		return retryInterval;
	}

	public void setRetryInterval(int retryInterval) {
		this.retryInterval = retryInterval;
	}


	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String getDisplayName() {
		return this.displayName;
	}

	@Override
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	@Override
	public String getDescription() {
		return this.description;
	}

	@Override
	public void setDescription(String description) {
		this.description = description;
	}

	public ArrayList<WebHookSubscriber> getSubscribers() {
		if (subscribers == null) {
			return new ArrayList<WebHookSubscriber>();
		}
		return subscribers;
	}

	public void setSubscribers(ArrayList<WebHookSubscriber> subscribers) {
		this.subscribers = subscribers;
	}
	
	public void addSubscriber(WebHookSubscriber newSubscriber) {
		if (this.subscribers==null) {
			this.subscribers = new ArrayList <WebHookSubscriber>();
		}
		this.subscribers.add(newSubscriber);
	}
	
	/***
	 * 特定のsubscriberオブジェを取得。
	 * 見つけないならnullが返される 
	 */
	public WebHookSubscriber getSubscriberById (String subscriberUid){
		
		for (WebHookSubscriber temp : subscribers) {
			if (temp.getWebHookSubscriberId()==subscriberUid) {
				return temp;
			}
		} 
		return null;
	}

	public Boolean isSynchronous() {
		if (synchronous == null) {
			return false;
		}
		
		return synchronous;
	}

	public void setSynchronous(Boolean synchronous) {
		this.synchronous = synchronous;
	}
	
	public ArrayList<WebHookHeader> getHeaders() {
		if (headers==null) {
			this.headers = new ArrayList<WebHookHeader>();
		}
		return headers;
	}

	public void setHeaders(ArrayList<WebHookHeader> headers) {
		this.headers = headers;
	}
	
	public void addHeaders(WebHookHeader entry) {
		if (headers == null) {
			headers = new ArrayList<WebHookHeader>();
		}
		this.headers.add(entry);
	}
	
	public String getMetaDataId() {
		return metaDataId;
	}

	public void setMetaDataId(String id) {
		this.metaDataId = id;
	}
	
	public String getTokenHeader() {
		return tokenHeader;
	}

	public void setTokenHeader(String tokenHeader) {
		this.tokenHeader = tokenHeader;
	}
	
	public String getHttpMethod() {
		return httpMethod;
	}

	public void setHttpMethod(String httpMethod) {
		this.httpMethod = httpMethod;
	}

}
