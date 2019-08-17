/**
 * Copyright 2016 Red Hat, Inc.
 *
 * Red Hat licenses this file to you under the Apache License, version
 * 2.0 (the "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or
 * implied.  See the License for the specific language governing
 * permissions and limitations under the License.
 */
package io.fabric8.maven.enricher.standard;

import io.fabric8.kubernetes.api.builder.TypedVisitor;
import io.fabric8.kubernetes.api.model.KubernetesListBuilder;
import io.fabric8.kubernetes.api.model.PodTemplateSpecBuilder;
import io.fabric8.maven.enricher.api.BaseEnricher;
import io.fabric8.maven.enricher.api.MavenEnricherContext;
import io.fabric8.maven.enricher.api.util.InitContainerHandler;

/**
 * @author ryan
 * @since 14/11/16
 */
public class InitContainerEnricher extends BaseEnricher {

    public static final String ENRICHER_NAME = "fmp-initContainer";
    //static final String VOLUME_STORAGE_CLASS_ANNOTATION = "volume.beta.kubernetes.io/storage-class";

    private final InitContainerHandler initContainerHandler;

    

    public InitContainerEnricher(MavenEnricherContext buildContext) {
        super(buildContext, ENRICHER_NAME);
        initContainerHandler = new InitContainerHandler(buildContext.getLog());
    }

    @Override
    public void adapt(KubernetesListBuilder builder) {

        builder.accept(new TypedVisitor<PodTemplateSpecBuilder>() {
            @Override
            public void visit(PodTemplateSpecBuilder builder) {                
                initContainerHandler.changeInitContainer(builder);                             
            }
            

        });

        
    }
}
