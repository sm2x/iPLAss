/*
 * Copyright (C) 2019 INFORMATION SERVICES INTERNATIONAL - DENTSU, LTD. All Rights Reserved.
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
package org.iplass.mtp.impl.auth.oauth.jwt;

public enum EllipticCurveSpec {
	
	P_256("P-256", 32),
	P_384("P-384", 48),
	P_521("P-521", 66);
	
	private final String curveName;
	private final int octetStringLength;
	
	private EllipticCurveSpec(String curveName, int octetStringLength) {
		this.curveName = curveName;
		this.octetStringLength = octetStringLength;
	}
	
    public String getCurveName() {
		return curveName;
	}
	public int getOctetStringLength() {
		return octetStringLength;
	}
	
	static EllipticCurveSpec preferredSpec(int keyLength) {
		if (keyLength >= 512) {
			return P_521;
		}
		if (keyLength >= 384) {
			return P_384;
		}
		
		return P_256;
	}

}
