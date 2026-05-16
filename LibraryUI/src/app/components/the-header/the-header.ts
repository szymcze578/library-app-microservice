import { Component } from '@angular/core';
import { RouterLink } from '@angular/router';

@Component({
  selector: 'app-the-header',
  imports: [RouterLink],
  templateUrl: './the-header.html',
  styleUrl: './the-header.css',
})
export class TheHeader {
  readonly locationIcon = 'https://www.figma.com/api/mcp/asset/8cbfab45-a4af-430a-99a5-701cc492ad2c';
  readonly basketIcon = 'https://www.figma.com/api/mcp/asset/8584d3cf-f8f9-4619-b73d-3bfb51b980a8';
  readonly forwardIcon = 'https://www.figma.com/api/mcp/asset/1e756d9d-6efd-4a30-84c0-2628b551eb1a';
  readonly userIcon = 'https://www.figma.com/api/mcp/asset/e6622289-9da0-4846-8cf7-921fc58615e2';
  readonly logo = 'https://www.figma.com/api/mcp/asset/acb6a1a1-16d9-4406-9d72-229b972b0692';
}
