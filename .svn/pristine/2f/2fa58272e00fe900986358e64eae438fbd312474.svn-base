/*************************************************
Copyright (C), 2012
Author:梁华璜 
Version: 
Date: 2012-6-14
Description: // 用于详细说明此程序文件完成的主要功能，与其他模块
// 或函数的接口，输出值、取值范围、含义及参数间的控
// 制、顺序、独立或依赖等关系
Function List: // 主要函数列表，每条记录应包括函数名及功能简要说明
1. ....
History: // 修改历史记录列表，每条修改记录应包括修改日期、修改
// 者及修改内容简述
1. Date:
Author:
Modification:
2. ...
 *************************************************/
package com.hcis.ipanther.common.excel.config;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;


import com.hcis.ipanther.common.excel.model.ModelStatement;

public class Configuration {

	protected final Map<String, ModelStatement> modelStatements = new StrictMap<String, ModelStatement>(
			"Model Statements collection");

	public void addModelStatement(ModelStatement ms) {
		modelStatements.put(ms.getId(), ms);
	}

	public Collection<ModelStatement> getmodelStatements() {
		return modelStatements.values();
	}

	public ModelStatement getModelStatement(String id) {
		return modelStatements.get(id);
	}

	protected static class StrictMap<J extends String, K extends Object>
			extends HashMap<J, K> {

		private String name;

		public StrictMap(String name, int initialCapacity, float loadFactor) {
			super(initialCapacity, loadFactor);
			this.name = name;
		}

		public StrictMap(String name, int initialCapacity) {
			super(initialCapacity);
			this.name = name;
		}

		public StrictMap(String name) {
			super();
			this.name = name;
		}

		public StrictMap(String name, Map<? extends J, ? extends K> m) {
			super(m);
			this.name = name;
		}

		public K put(J key, K value) {
			if (containsKey(key))
				throw new IllegalArgumentException(name
						+ " already contains value for " + key);
			if (key.contains(".")) {
				final String shortKey = getShortName(key);
				if (super.get(shortKey) == null) {
					super.put((J) shortKey, value);
				} else {
					super.put((J) shortKey, (K) new Ambiguity(shortKey));
				}
			}
			return super.put(key, value);
		}

		public K get(Object key) {
			K value = super.get(key);
			if (value == null) {
				value = super.get(getShortName((J) key));
				if (value == null) {
					throw new IllegalArgumentException(name
							+ " does not contain value for " + key);
				}
			}
			if (value instanceof Ambiguity) {
				throw new IllegalArgumentException(
						((Ambiguity) value).getSubject()
								+ " is ambiguous in "
								+ name
								+ " (try using the full name including the namespace, or rename one of the entries)");
			}
			return value;
		}

		private String getShortName(J key) {
			final String[] keyparts = key.split("\\.");
			final String shortKey = keyparts[keyparts.length - 1];
			return shortKey;
		}

		protected static class Ambiguity {
			private String subject;

			public Ambiguity(String subject) {
				this.subject = subject;
			}

			public String getSubject() {
				return subject;
			}
		}
	}
}
