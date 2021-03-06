/*
 * Copyright (c) 2017 Baidu, Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.elasticsearch.rest.action.admin.cluster.node.delete;

import static org.elasticsearch.rest.RestRequest.Method.DELETE;

import org.elasticsearch.action.admin.cluster.node.delete.DeleteNodeRequest;
import org.elasticsearch.action.admin.cluster.node.delete.DeleteNodeResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.common.inject.Inject;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.rest.BaseRestHandler;
import org.elasticsearch.rest.RestChannel;
import org.elasticsearch.rest.RestController;
import org.elasticsearch.rest.RestRequest;
import org.elasticsearch.rest.action.support.AcknowledgedRestListener;

public class RestDeleteNodesAction extends BaseRestHandler {

    @Inject
    public RestDeleteNodesAction(Settings settings, RestController controller, Client client) {
        super(settings, controller, client);
        controller.registerHandler(DELETE, "/_nodes/{nodeIpPort}", this);
    }

    @Override
    public void handleRequest(final RestRequest request, final RestChannel channel, final Client client) {
        String ipPort = request.param("nodeIpPort");
        DeleteNodeRequest deleteNodeRequest = new DeleteNodeRequest(ipPort);
        client.admin().cluster().deleteNodes(deleteNodeRequest, new AcknowledgedRestListener<DeleteNodeResponse>(channel));
    }
}