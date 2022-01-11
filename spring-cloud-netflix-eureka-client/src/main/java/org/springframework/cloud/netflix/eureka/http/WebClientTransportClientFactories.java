/*
 * Copyright 2017-2022 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.cloud.netflix.eureka.http;

import java.util.Collection;
import java.util.Optional;
import java.util.function.Supplier;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClientConfig;
import com.netflix.discovery.shared.transport.TransportClientFactory;
import com.netflix.discovery.shared.transport.jersey.EurekaJerseyClient;
import com.netflix.discovery.shared.transport.jersey.TransportClientFactories;

import org.springframework.web.reactive.function.client.WebClient;

/**
 * @author Daniel Lavoie
 * @author Haytham Mohamed
 */
public class WebClientTransportClientFactories implements TransportClientFactories<Void> {

	private final Supplier<WebClient.Builder> builder;

	public WebClientTransportClientFactories(Supplier<WebClient.Builder> builder) {
		this.builder = builder;
	}

	@Override
	public TransportClientFactory newTransportClientFactory(Collection<Void> additionalFilters,
			EurekaJerseyClient providedJerseyClient) {
		throw new UnsupportedOperationException();
	}

	@Override
	public TransportClientFactory newTransportClientFactory(EurekaClientConfig clientConfig,
			Collection<Void> additionalFilters, InstanceInfo myInstanceInfo) {
		return new WebClientTransportClientFactory(builder);
	}

	@Override
	public TransportClientFactory newTransportClientFactory(final EurekaClientConfig clientConfig,
			final Collection<Void> additionalFilters, final InstanceInfo myInstanceInfo,
			final Optional<SSLContext> sslContext, final Optional<HostnameVerifier> hostnameVerifier) {
		return new WebClientTransportClientFactory(builder);
	}

}
