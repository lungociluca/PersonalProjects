#version 410 core

layout(location=0) in vec3 vPosition;
layout(location=1) in vec3 vNormal;
layout(location=2) in vec2 vTexCoords;

out vec3 fPosition;
out vec3 fNormal;
out vec2 fTexCoords;
out vec4 fPosLightSpace;

uniform mat4 model;
uniform mat4 view;
uniform mat4 lightSpaceTrMatrix;
uniform mat4 projection;

void main() 
{
	fPosLightSpace = lightSpaceTrMatrix * model * vec4(vPosition, 1.0f);
	gl_Position = projection * view * model * vec4(vPosition, 1.0f);
	fPosition = vPosition;
	fNormal = vec3(model * vec4(vPosition,1.0));
	fTexCoords = vTexCoords;
}
