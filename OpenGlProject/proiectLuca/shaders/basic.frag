#version 410 core

in vec3 fPosition;
in vec3 fNormal;
in vec2 fTexCoords;
in vec4 fPosLightSpace;
out vec4 fColor;



float shadow = 0.0f;
float shininess = 32;
//matrices
uniform mat4 model;
uniform mat4 view;
uniform mat3 normalMatrix;

//lighting
uniform vec3 lightDir;
uniform vec3 lightColor;
uniform vec3 lightPosition;
// textures

uniform sampler2D diffuseTexture;
uniform sampler2D specularTexture;
uniform sampler2D shadowMap;

//components
vec3 ambient;
float ambientStrength = 0.2f;
vec3 diffuse;
vec3 specular;
float specularStrength = 0.5f;

void computeDirLight()
{
    //compute eye space coordinates
    vec4 fPosEye = view * model * vec4(fPosition, 1.0f);
    vec3 normalEye = normalize(normalMatrix * fNormal);

    //normalize light direction
    vec3 lightDirN = vec3(normalize(view * vec4(lightDir, 0.0f)));

    //compute view direction (in eye coordinates, the viewer is situated at the origin
    vec3 viewDir = normalize(- fPosEye.xyz);

    //compute ambient light
    ambient = ambientStrength * lightColor;

    //compute diffuse light
    diffuse = max(dot(normalEye, lightDirN), 0.0f) * lightColor;

    //compute specular light
    vec3 reflectDir = reflect(-lightDirN, normalEye);
    float specCoeff = pow(max(dot(viewDir, reflectDir), 0.0f), 32);
    specular = specularStrength * specCoeff * lightColor;
}

float computeShadow()
{

	vec3 ndc= fPosLightSpace.xyz / fPosLightSpace.w;
	ndc = ndc * 0.5 + 0.5;
	float closestDepth = texture(shadowMap, ndc.xy).r;
	float currentDepth = ndc.z;
	float bias = 0.005f;
	float shadow = currentDepth - bias > closestDepth ? 1.0 : 0.0;
	if (ndc.z > 1.0f)
		return 0.0f;
	return shadow;

}

float computeFog()
{
 float fogDensity = 0.05f;
 vec4 fPosEye = view * model * vec4(fPosition, 1.0f);
 float fragmentDistance = length(fPosEye);
 float fogFactor = exp(-pow(fragmentDistance * fogDensity, 2));
 
 return clamp(fogFactor, 0.0f, 1.0f);
}


float constant = 1.0f;
float linear = 0.0045f;
float quadratic = 0.0075f;
vec3 computePointLight()
{   
    //compute eye space coordinates
    vec4 fPosEye = view * model * vec4(fPosition, 1.0f);

    vec4 lightPosEye = view * vec4(lightPosition,1.0f);

    vec3 normalEye = normalize(normalMatrix * fNormal);

    //normalize light direction
    vec3 lightDirN = normalize(lightPosition.xyz - fPosition.xyz);

    //compute view direction (in eye coordinates, the viewer is situated at the origin
    vec3 viewDir = normalize(- fPosEye.xyz);

    //compute ambient light
     vec3  ambientNew = ambientStrength * lightColor;

    //compute diffuse light
    vec3 diffuseNew = max(dot(normalEye, lightDirN), 0.0f) * lightColor;
    
    vec3 halfVector = normalize(lightDirN + viewDir);

    //compute specular light
    float specCoeff = pow(max(dot(normalEye, halfVector), 0.0f), shininess);
    vec3 specularNew = specularStrength * specCoeff * lightColor;

    float distance    = length(lightPosition - fPosition);
    float attenuation = 1.0 / (constant + linear * distance + quadratic * (distance * distance));  

    return ambientNew * attenuation + diffuseNew * attenuation + specularNew * attenuation;
}

void main() 
{
    float fogFactor = computeFog();
    vec4 fogColor = vec4(0.5f, 0.5f, 0.5f, 1.0f);
    ambient *= texture(diffuseTexture, fTexCoords).rgb;
    diffuse *= texture(diffuseTexture, fTexCoords).rgb;
    specular *= texture(specularTexture, fTexCoords).rgb;

    computeDirLight();

    //compute final vertex color
    shadow = computeShadow();
    vec3 color = min((ambient + (1.0f - shadow) * diffuse) * texture(diffuseTexture, fTexCoords).rgb + (1.0f - shadow) * specular * texture(specularTexture, fTexCoords).rgb + computePointLight(), 1.0f);

    vec4 withoutFog = vec4(color, 1.0f);
    fColor = mix(fogColor, withoutFog, fogFactor);
}
